package com.example.kodetrainee.repository

import com.example.kodetrainee.domain.model.User
import io.reactivex.rxjava3.core.Maybe

interface UserRepository {
    fun getUserList(): Maybe<List<User>>
}