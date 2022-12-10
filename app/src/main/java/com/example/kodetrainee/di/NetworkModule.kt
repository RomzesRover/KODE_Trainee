package com.example.kodetrainee.di

import com.example.kodetrainee.network.api.ApiNetworkRetrofit
import com.example.kodetrainee.network.utils.UserNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideEntityMapper(): UserNetworkMapper{
        return UserNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideApiNetworkRetrofit(): ApiNetworkRetrofit{
        return Retrofit.Builder()
            .baseUrl("https://stoplight.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiNetworkRetrofit::class.java)
    }
}