package com.example.workman.Retrofit

object Common {
    var selected_Group_Number: Int = 0

    val api:ServicApi
        get() {
            val retrofit =  RetrofitClient.instance
            return retrofit.create(ServicApi::class.java)
        }
}