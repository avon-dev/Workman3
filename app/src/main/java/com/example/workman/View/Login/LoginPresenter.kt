package com.example.workman.View.Login

import com.example.workman.Model.User

class LoginPresenter(val ILoginView: LoginContract.ILoginView):
    LoginContract.ILoginPresenter{
    override fun onLogin(email: String, password: String) {
        val user = User(email, password)
        val loginCode = user.LoginDataValid()
        if (loginCode == 0)
            ILoginView.onLoginError("이메일을 입력하세요.")
        else if (loginCode == 1)
            ILoginView.onLoginError("이메일 형식이 맞지않습니다.")
        else if (loginCode == 2)
            ILoginView.onLoginError("비밀번호 6자리 이상 입력하세요.")
        else
            ILoginView.onLoginSuccess("로그인 성공.")


    }


}