package com.example.workman.Network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BaseURL = "http://54.180.89.175:3000/"
    private var retrofit: Retrofit? = null
    val instance: Retrofit
        get() {
            if (retrofit == null)
                retrofit = Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit!!
        }
}




