package com.example.kodetrainee.repository

import com.example.kodetrainee.domain.model.User
import com.example.kodetrainee.network.api.ApiNetworkRetrofit
import com.example.kodetrainee.network.utils.UserNetworkMapper
import io.reactivex.rxjava3.core.Maybe

class UserRepositoryImpl( private val apiNetworkRetrofit: ApiNetworkRetrofit, private val mapper: UserNetworkMapper): UserRepository {

    override fun getUserList(): Maybe<List<User>> {
        return apiNetworkRetrofit.getUserList().flatMap {
            Maybe.just(mapper.mapFromNetworkEntityListToDomainModelList(it.items))
        }
    }
}