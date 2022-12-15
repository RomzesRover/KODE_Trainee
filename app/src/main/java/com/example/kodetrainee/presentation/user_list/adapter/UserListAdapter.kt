package com.example.kodetrainee.presentation.user_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kodetrainee.databinding.ListItemUserBinding
import com.example.kodetrainee.domain.model.User

class UserListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userListShowing: ArrayList<User> = arrayListOf()

    fun setupUserList(newList: List<User>){
        val diffResult = DiffUtil.calculateDiff(UserListDiffUtil(userListShowing, newList))
        userListShowing.clear()
        userListShowing.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val listItemUserBinding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListUserViewHolder(listItemUserBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserListUserViewHolder){
            holder.setupUserItem(userListShowing[position])
        }
    }

    override fun getItemCount(): Int {
        return userListShowing.count()
    }
}