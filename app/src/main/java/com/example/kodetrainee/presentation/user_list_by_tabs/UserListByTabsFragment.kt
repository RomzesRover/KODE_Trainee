package com.example.kodetrainee.presentation.user_list_by_tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kodetrainee.databinding.FragmentUserListByTabsBinding
import com.example.kodetrainee.presentation.user_list_by_tabs.adapter.UserListByTabsStateAdapter

class UserListByTabsFragment: Fragment() {

    private val viewModel: UserListByTabsViewModel by viewModels()

    private lateinit var pagerAdapter: UserListByTabsStateAdapter

    private var _binding: FragmentUserListByTabsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentUserListByTabsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUserListPager()
        startObserveViewModel()
    }

    private fun initUserListPager(){
        pagerAdapter = UserListByTabsStateAdapter(this)
        binding.userListPager.adapter = pagerAdapter
    }

    private fun startObserveViewModel(){
        viewModel.fragmentsList.observe(viewLifecycleOwner) {
            pagerAdapter.setupFragmentsList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}