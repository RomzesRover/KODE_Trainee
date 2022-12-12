package com.example.kodetrainee.presentation.user_list_by_tabs.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kodetrainee.presentation.user_list_by_tabs.UserListByTabsFragment

class UserListByTabsStateAdapter(fragment: UserListByTabsFragment): FragmentStateAdapter(fragment) {

    private var fragmentsList: ArrayList<Fragment> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setupFragmentsList(newListFragment: List<Fragment>){
        fragmentsList.clear()
        fragmentsList.addAll(newListFragment)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fragmentsList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }
}