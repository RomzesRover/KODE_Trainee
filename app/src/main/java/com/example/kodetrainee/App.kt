package com.example.kodetrainee

import android.app.Application
import com.example.kodetrainee.network.api.ApiNetworkRetrofit
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    lateinit var apiNetworkRetrofit: ApiNetworkRetrofit

    override fun onCreate() {
        super.onCreate()

        //Create global api instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://stoplight.io")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        apiNetworkRetrofit = retrofit.create(ApiNetworkRetrofit::class.java)
    }
}