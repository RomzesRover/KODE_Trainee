package com.example.kodetrainee.network.model

import com.google.gson.annotations.SerializedName

data class UsersNetworkList(
    @SerializedName("items") var items: List<UserNetworkEntity> = emptyList()
)