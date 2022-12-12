package com.example.kodetrainee.presentation.user_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kodetrainee.databinding.ListItemUserBinding
import com.example.kodetrainee.domain.model.User

class UserListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var userListShowing: ArrayList<User> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setupUserList(newList: List<User>){
        userListShowing.clear()
        userListShowing.addAll(newList)
        // TODO: Use diff util: possible delete notify data set changed
        notifyDataSetChanged()
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