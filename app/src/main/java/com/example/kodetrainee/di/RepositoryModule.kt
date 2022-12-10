package com.example.kodetrainee.di

import com.example.kodetrainee.network.api.ApiNetworkRetrofit
import com.example.kodetrainee.network.utils.UserNetworkMapper
import com.example.kodetrainee.repository.UserRepository
import com.example.kodetrainee.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(apiNetworkRetrofit: ApiNetworkRetrofit, mapper: UserNetworkMapper): UserRepository {
        return UserRepositoryImpl(apiNetworkRetrofit, mapper)
    }

}