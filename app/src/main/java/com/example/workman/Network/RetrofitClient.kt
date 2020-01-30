package com.example.workman.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://ec2-54-180-145-251.ap-northeast-2.compute.amazonaws.coms:3000"
    private var retrofit: Retrofit? = null
    val apiClient: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
//private const val BASE_URL = "localhost:3000"


