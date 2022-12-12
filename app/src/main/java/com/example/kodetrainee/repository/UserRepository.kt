package com.example.kodetrainee.repository

import com.example.kodetrainee.domain.model.Department
import com.example.kodetrainee.domain.model.User
import io.reactivex.rxjava3.core.Maybe

interface UserRepository {
    fun getUserList(department: Department): Maybe<List<User>>
}