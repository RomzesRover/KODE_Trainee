package com.example.kodetrainee.network.model

import com.example.kodetrainee.network.model.UserNetworkEntity
import com.google.gson.annotations.SerializedName

data class UsersNetworkList(
    @SerializedName("items") var items: List<UserNetworkEntity> = emptyList()
)