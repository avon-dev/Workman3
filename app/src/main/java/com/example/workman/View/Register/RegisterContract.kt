package com.example.workman.View.Register

interface RegisterContract {

    interface IRegisterPresenter{
        fun onRegister(email:String,password:String,password2:String,name:String,phonenumber:String)
    }

    interface IRegisterView{
        fun onRegisterSuccess(message: String)
        fun onRegisterError(message: String)

    }
}