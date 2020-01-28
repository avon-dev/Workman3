package com.example.workman.View.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.workman.MainActivity
import com.example.workman.R
import com.example.workman.View.Login.LoginActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterContract.IRegisterView {
    private lateinit var registerPresenter: RegisterContract.IRegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerPresenter = RegisterPresenter(this)

        regster_complete_btn.setOnClickListener {
          registerPresenter.onRegister(register_email.text.toString(),register_password.text.toString(),register_password2.text.toString(),register_username.text.toString(),register_phonenumber.text.toString())
        }
    }

    //로그인 성공시 홈화면으로 이동
    override fun onRegisterSuccess(message: String) {
        Toasty.success(this,message, Toast.LENGTH_SHORT).show()
        val CompleteRegister = Intent(this, LoginActivity::class.java)
        startActivity(CompleteRegister)
    }
    //로그인 실패시 에러 메시지 작동
    override fun onRegisterError(message: String) {
        Toasty.error(this,message, Toast.LENGTH_SHORT).show()
    }

}
