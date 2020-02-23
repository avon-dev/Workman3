package com.example.workman.Retrofit

import com.example.workman.Model.C_Group
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ServicApi {

    @POST("c_group_up")
    @FormUrlEncoded
    fun c_group_up(@Field("name") name:String,
                   @Field("user") user:String,
                   @Field("number") number:Int,
                   @Field("calendar") calendar:String): Observable<String>

    @POST("c_group")
    @FormUrlEncoded
    fun c_group(@Field("name") name:String): Observable<ArrayList<C_Group>>
}