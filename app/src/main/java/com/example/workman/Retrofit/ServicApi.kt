package com.example.workman.Retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ServicApi {

    @POST("login_user")
    @FormUrlEncoded
    fun login_user(@Field("name") email:String,
                   @Field("email") name:String): Observable<String>

    @GET("login_name")
    fun login_name(): Observable<String>
}