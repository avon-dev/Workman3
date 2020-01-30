package com.example.workman.Model

import android.text.TextUtils
import android.util.Patterns
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User(val email: String, val password: String, val password2:String? = null, val name:String? = null, val phone_number: String? = null){

    @Expose
    @SerializedName("UserEmail")
    private var userId :String? = null
    @Expose
    @SerializedName("UserPwd")
    private var userPassword: String? = null
    @Expose
    @SerializedName("UserName")
    private var userName: String? = null
    @Expose
    @SerializedName("UserPhoneNumber")
    private var userPhoneNumber: String? = null

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun getUserPassword(): String? {
        return userPassword
    }

    fun setUserPassword(userPassword: String?) {
        this.userPassword = userPassword
    }

    fun getUserName(): String? {
        return userName
    }

    fun setUserName(userName: String?) {
        this.userName = userName
    }

    fun getUserPhoneNumber(): String? {
        return userPhoneNumber
    }

    fun setUserPhoneNumber(userPhoneNumber: String?) {
        this.userPhoneNumber = userPhoneNumber
    }






    //로그인기능 예외처리
    fun LoginDataValid():Int {
       //TextUtils를 사용해서 해당 textview에 빈값인지 확인한다.
       return if(TextUtils.isEmpty(email))
           0
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
           1
       else if(password.length <= 6)
           2
       else
           -1
    }
    fun registerDataValid():Int {
        return if(TextUtils.isEmpty(email))
            0
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            1
        else if(password.length <= 6)
            2
        else if (password != password2)
            3
        else
            -1


    }

}


