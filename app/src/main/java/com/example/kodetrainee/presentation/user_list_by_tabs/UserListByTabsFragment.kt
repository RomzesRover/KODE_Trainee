package com.example.kodetrainee.presentation.user_list_by_tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.kodetrainee.databinding.FragmentUserListByTabsBinding
import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.presentation.MainActivityViewModel
import com.example.kodetrainee.presentation.user_list_by_tabs.adapter.UserListByTabsStateAdapter

class UserListByTabsFragment: Fragment() {

    private val viewModel: UserListByTabsViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()

    private lateinit var pagerAdapter: UserListByTabsStateAdapter
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
    }

    private fun initUserListPager(departmentsList: ArrayList<Department>){
        pagerAdapter = UserListByTabsStateAdapter(this, departmentsList)
        binding.userListPager.offscreenPageLimit = offscreenPageLimit
        binding.userListPager.adapter = pagerAdapter
        handleDragging()
    }

    private fun startObserveViewModel(){
        viewModel.departmentsList.observe(viewLifecycleOwner) {
            initUserListPager(it)
        }
    }

    private fun handleDragging() {
        binding.userListPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state){
                    ViewPager2.SCROLL_STATE_DRAGGING, ViewPager2.SCROLL_STATE_SETTLING -> {
                        activityViewModel.rejectBackgroundHeavyLoad()
                    }
                    ViewPager2.SCROLL_STATE_IDLE -> activityViewModel.allowBackgroundHeavyLoad()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}