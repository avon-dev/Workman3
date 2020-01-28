package com.example.workman.View.Register

import com.example.workman.Model.User

class RegisterPresenter(val IRegisterView: RegisterContract.IRegisterView) :
    RegisterContract.IRegisterPresenter {
    override fun onRegister(email: String,password: String,password2: String,name: String,phone_number: String) {
       val user = User(email,password,password2,name,phone_number)
       val registerCode = user.registerDataValid()
       if(registerCode == 0)
            IRegisterView.onRegisterError("이메일을 입력하세요")
       else if(registerCode == 1)
            IRegisterView.onRegisterError("이메일 형식에 맞게 입력하세요")
        else if(registerCode == 2)
            IRegisterView.onRegisterError("비밀번호는 6~12자리로 입력하세요")
        else if(registerCode == 3)
           IRegisterView.onRegisterError("비밀번호가 일치하지않습니다. 다시 입력하세요")
        else
           IRegisterView.onRegisterSuccess("회원가입성공")




    }
}