package com.example.kodetrainee.presentation.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kodetrainee.R
import com.example.kodetrainee.databinding.FragmentUserListBinding
import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.presentation.MainActivityViewModel
import com.example.kodetrainee.presentation.user_list.adapter.UserListAdapter
import com.example.kodetrainee.presentation.user_list_by_tabs.UserListByTabsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment: Fragment() {

    companion object {
        const val SKELETON_ITEMS_SIZE = 15
        const val REQUIRED_DEPARTMENT_KEY = "UserListViewModel:REQUIRED_DEPARTMENT_KEY"

        @JvmStatic
        fun newInstance(department: Department) = UserListFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REQUIRED_DEPARTMENT_KEY, department)
            }
        }
    }

    private val viewModel: UserListViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private val userListByTabsViewModel: UserListByTabsViewModel by activityViewModels()

    private val userListAdapter = UserListAdapter()

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startObserveActivityViewModel()
        initUserListRecyclerView()
        startObserveViewModel()
    }

    private fun initUserListRecyclerView(){
        binding.userListRecyclerView.apply {

            val paddingTopBottom = resources.getDimensionPixelSize(R.dimen.fragment_user_list_padding_top_bottom)
            getRecyclerView().setPadding(0, paddingTopBottom, 0, paddingTopBottom)
            getRecyclerView().clipToPadding = false
            getRecyclerView().setHasFixedSize(true)

            getVeiledRecyclerView().setPadding(0, paddingTopBottom, 0, paddingTopBottom)
            getVeiledRecyclerView().clipToPadding = false

            setAdapter(userListAdapter)
            setLayoutManager(LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false))
            addVeiledItems(SKELETON_ITEMS_SIZE)
        }
    }

    private fun startObserveViewModel(){
        viewModel.userList.observe(viewLifecycleOwner) {
            userListAdapter.setupUserList(it)
            binding.userListRecyclerView.unVeil()
        }

        viewModel.searchResultEmpty.observe(viewLifecycleOwner){
            showSearchResultEmptyMessage()
        }

        viewModel.searchResultNotEmpty.observe(viewLifecycleOwner){
            hideSearchResultEmptyMessage()
        }
    }

    private fun startObserveActivityViewModel(){
        activityViewModel.backgroundHeavyLoad.observe(viewLifecycleOwner){
            viewModel.setIsBackgroundLoadAllowed(it)
        }

        userListByTabsViewModel.searchQuery.observe(viewLifecycleOwner){
            viewModel.filterListByString(it.first, it.second)
        }
    }

    private fun showSearchResultEmptyMessage(){
        binding.userListEmptyMessageLayout.visibility = View.VISIBLE
    }

    private fun hideSearchResultEmptyMessage(){
        binding.userListEmptyMessageLayout.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}