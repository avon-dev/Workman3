package com.example.workman.Network


import com.example.workman.data.JoinData
import com.example.workman.data.JoinResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ServiceApi {
    @POST("/user/join")
    fun userJoin(@Body data: JoinData?): Call<JoinResponse?>?
}