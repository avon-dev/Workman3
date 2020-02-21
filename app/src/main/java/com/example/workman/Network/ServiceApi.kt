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

    @POST("create_company")
    @FormUrlEncoded
    fun Create_Company(
        @Field("company_name") company_name:String?,
        @Field("people_num") people_num:String?,
        @Field("cp_lat") cp_lat: String?,
        @Field("cp_lng") cp_lng:String?,
        @Field("company_address") company_address : String?,
        @Field("owner_uid") owner_uid : String?,
        @Field("company_code") company_code : String?,
        @Field("cp_group") cp_group : String?
    ):Observable<String>
}