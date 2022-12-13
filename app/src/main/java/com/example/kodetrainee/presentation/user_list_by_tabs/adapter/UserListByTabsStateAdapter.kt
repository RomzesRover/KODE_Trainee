package com.example.kodetrainee.presentation.user_list_by_tabs.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.presentation.user_list.UserListFragment
import com.example.kodetrainee.presentation.user_list_by_tabs.UserListByTabsFragment

class UserListByTabsStateAdapter(fragment: UserListByTabsFragment, private val departmentList: List<Department>): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return departmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return UserListFragment.newInstance(departmentList[position])
    }
}