package com.example.kodetrainee.network.api

import com.example.kodetrainee.network.model.UsersNetworkList
import io.reactivex.rxjava3.core.Maybe
import retrofit2.http.GET

interface ApiNetworkRetrofit {
    @GET("/mocks/kode-education/trainee-test/25143926/users")
    fun getUserList(): Maybe<UsersNetworkList>
}