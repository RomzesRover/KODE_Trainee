package com.example.kodetrainee.network.model

import com.google.gson.annotations.SerializedName

data class UserNetworkEntity(
    @SerializedName("id") var id: String = "",
    @SerializedName("avatarUrl") var avatarUrl: String = "",
    @SerializedName("firstName") var firstName: String = "",
    @SerializedName("lastName") var lastName: String = "",
    @SerializedName("userTag") var userTag: String = "",
    @SerializedName("department") var department: String = "",
    @SerializedName("position") var position: String = "",
    @SerializedName("birthday") var birthday: String = "",
    @SerializedName("phone") var phone: String = ""
)