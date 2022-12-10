package com.example.kodetrainee.domain.model

data class User (
    var id: String = "",
    var avatarUrl: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var userTag: String = "",
    var department: Department = Department.Unknown(),
    var position: String = "",
    var birthday: String = "",
    var phone: String = ""
)