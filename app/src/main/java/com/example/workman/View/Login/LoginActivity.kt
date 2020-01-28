package com.example.workman.View.Login

//import com.example.workman.View.Register.RegisterActivity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.workman.MainActivity
import com.example.workman.R
import com.example.workman.View.Register.RegisterActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.ILoginView {
    private lateinit var loginPresenter: LoginContract.ILoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //init
        loginPresenter = LoginPresenter(this)

        //로그인 버튼 클릭event
        login_btn.setOnClickListener{
            loginPresenter.onLogin(login_email.text.toString(),login_password.text.toString())
        }

        register_btn.setOnClickListener {
            val registerIntent = Intent(this,
                RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }

    //로그인 성공시 홈화면으로 이동
    override fun onLoginSuccess(message: String) {
        Toasty.success(this,message,Toast.LENGTH_SHORT).show()
        val HomeIntent = Intent(this,MainActivity::class.java)
        startActivity(HomeIntent)
    }
    //로그인 실패시 에러 메시지 작동
   override fun onLoginError(message: String) {
        Toasty.error(this,message,Toast.LENGTH_SHORT).show()

    }

}


