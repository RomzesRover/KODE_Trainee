package com.example.kodetrainee.presentation.user_list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.kodetrainee.R
import com.example.kodetrainee.databinding.ListItemUserBinding
import com.example.kodetrainee.domain.model.User

class UserListUserViewHolder(private val itemBinding: ListItemUserBinding): RecyclerView.ViewHolder(itemBinding.root) {

    fun setupUserItem(user: User){
        itemBinding.userItemFullName.text = user.getFullName()
        itemBinding.userItemTag.text = user.userTag.lowercase()
        itemBinding.userItemDepartment.text = itemView.context.getString(user.department.nameResourceId)

        Glide.with(itemView)
            .load(user.avatarUrl)
            .circleCrop()
            .placeholder(R.drawable.ic_user_avatar_thumbnail)
            .error(R.drawable.ic_user_avatar_failed)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemBinding.userItemAvatar)
    }

}