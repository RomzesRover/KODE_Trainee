package com.example.kodetrainee.presentation.user_list_by_tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.kodetrainee.R
import com.example.kodetrainee.databinding.FragmentUserListByTabsBinding
import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.presentation.MainActivityViewModel
import com.example.kodetrainee.presentation.user_list_by_tabs.adapter.UserListByTabsStateAdapter
import jp.co.cyberagent.android.tabanimation.setupAnimationTabWithViewPager
import jp.co.cyberagent.android.tabanimation.viewIdAnimationInfo

class UserListByTabsFragment: Fragment() {

    private val viewModel: UserListByTabsViewModel by activityViewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var pagerAdapter: UserListByTabsStateAdapter
    private lateinit var viewPagerOnPageChangeCallback: ViewPager2.OnPageChangeCallback
    private val offscreenPageLimit: Int = 5

    private var _binding: FragmentUserListByTabsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserListByTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startObserveViewModel()
        handleSearchAction()
        initSearchViewAppearance()
    }

    private fun initSearchViewAppearance(){
        val searchIcon = binding.topSearchEditText.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)

        searchIcon.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.fragment_user_list_by_tabs_search_view_search_icon_inactive_color),
            android.graphics.PorterDuff.Mode.SRC_IN)

        searchIcon.updateLayoutParams {
            width = resources.getDimensionPixelSize(R.dimen.fragment_user_list_by_tabs_search_view_search_icon_size)
            height = resources.getDimensionPixelSize(R.dimen.fragment_user_list_by_tabs_search_view_search_icon_size)
        }
    }

    private fun initUserListPager(departmentsList: ArrayList<Department>){
        pagerAdapter = UserListByTabsStateAdapter(this, departmentsList)
        binding.userListPager.offscreenPageLimit = offscreenPageLimit
        binding.userListPager.adapter = pagerAdapter

        initTabLayoutWithViewPager(departmentsList)
        handlePagerActions()
    }

    private fun initTabLayoutWithViewPager(departmentsList: ArrayList<Department>){
        val animationInfo = viewIdAnimationInfo {
            animate<TextView, Float>(R.id.listItemCustomTabTextSelected) {
                property(View.ALPHA)
                startValue(0f)
                endValue(1f)
            }
            animate<TextView, Float>(R.id.listItemCustomTabTextUnSelected) {
                property(View.ALPHA)
                startValue(1f)
                endValue(0f)
            }
        }

        binding.tabLayout.setupAnimationTabWithViewPager(binding.userListPager, animationInfo, R.layout.list_item_custom_tab){ tab, view, position ->
            view.findViewById<TextView>(R.id.listItemCustomTabTextSelected).text = getString(departmentsList[position].nameResourceId)
            view.findViewById<TextView>(R.id.listItemCustomTabTextUnSelected).text = getString(departmentsList[position].nameResourceId)
            tab.text = getString(departmentsList[position].nameResourceId)
        }
    }

    private fun startObserveViewModel(){
        viewModel.departmentsList.observe(viewLifecycleOwner) {
            initUserListPager(it)
        }

        viewModel.searchStateActive.observe(viewLifecycleOwner){
            setSearchIconToActive()
        }

        viewModel.searchStateInactive.observe(viewLifecycleOwner){
            setSearchIconToInactive()
        }
    }

    private fun handlePagerActions() {
        viewPagerOnPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state){
                    ViewPager2.SCROLL_STATE_DRAGGING, ViewPager2.SCROLL_STATE_SETTLING -> {
                        activityViewModel.rejectBackgroundHeavyLoad()
                    }
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        activityViewModel.allowBackgroundHeavyLoad()
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                cancelSearchQuery(position)
            }
        }

        binding.userListPager.registerOnPageChangeCallback(viewPagerOnPageChangeCallback)
    }

    private fun cancelSearchQuery(position: Int){
        binding.topSearchEditText.setQuery("", false)
        binding.topSearchEditText.clearFocus()
        viewModel.newSearchQuery("", position)
    }

    private fun handleSearchAction(){
        binding.topSearchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.topSearchEditText.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.newSearchQuery(newText, binding.userListPager.currentItem)
                return false
            }
        })
    }

    private fun setSearchIconToActive(){
        val searchIcon = binding.topSearchEditText.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)

        searchIcon.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.fragment_user_list_by_tabs_search_view_search_icon_active_color),
            android.graphics.PorterDuff.Mode.SRC_IN)
    }

    private fun setSearchIconToInactive(){
        val searchIcon = binding.topSearchEditText.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)

        searchIcon.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.fragment_user_list_by_tabs_search_view_search_icon_inactive_color),
            android.graphics.PorterDuff.Mode.SRC_IN)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.userListPager.unregisterOnPageChangeCallback(viewPagerOnPageChangeCallback)
        _binding = null
    }
}