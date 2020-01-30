package com.example.workman.data

import com.google.gson.annotations.SerializedName

class JoinData {
    @SerializedName("UserEmail")
    private var userEmail: String? = null

    @SerializedName("UserPwd")
    private var userPwd: String? = null

    @SerializedName("UserName")
    private var userName: String? = null

    @SerializedName("UserPhoneNumber")
    private var userPhoneNumber: String? =null

    fun JoinData(
        userName: String?,
        userEmail: String?,
        userPwd: String?,
        userPhoneNumber: String?
    ) {
        this.userName = userName
        this.userEmail = userEmail
        this.userPwd = userPwd
        this.userPhoneNumber = userPhoneNumber
    }
}