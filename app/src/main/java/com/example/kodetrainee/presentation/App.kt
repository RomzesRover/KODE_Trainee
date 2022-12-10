package com.example.kodetrainee.presentation

import android.app.Application
import com.example.kodetrainee.network.api.ApiNetworkRetrofit
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class App: Application()