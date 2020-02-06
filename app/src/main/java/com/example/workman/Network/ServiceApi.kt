package com.example.workman.Network



import com.google.gson.JsonObject
import io.reactivex.Observable

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ServiceApi {
    @POST("register")
    @FormUrlEncoded
    fun registerUser(
        @Field("UserEmail") UserEmail: String?,
        @Field("UserPwd") UserPwd: String?,
        @Field("UserName") UserName: String?,
        @Field("UserPhoneNumber") UserPhoneNumber: String?
    ):Observable<JsonObject>

    @POST("login")
    @FormUrlEncoded
    fun loginUser(
    )
}