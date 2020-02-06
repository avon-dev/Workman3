package com.example.workman.View.Register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workman.Network.Common
import com.example.workman.Network.ServiceApi
import com.example.workman.R
import es.dmoral.toasty.Toasty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity(), RegisterContract.IRegisterView {
    private lateinit var registerPresenter: RegisterContract.IRegisterPresenter

    //메모리누수막기
    var compositeDisposable = CompositeDisposable()
    lateinit var  registerapi:ServiceApi
    private var mProgressView: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerapi= Common.registerapi

        mProgressView = findViewById<View>(R.id.join_progress) as ProgressBar


        registerPresenter = RegisterPresenter(this)

        regster_complete_btn.setOnClickListener {
            register( register_email.text.toString(),
                register_password.text.toString(),
                register_username.text.toString(),
                register_phonenumber.text.toString())



            registerPresenter.onRegister(
                register_email.text.toString(),
                register_password.text.toString(),
                register_password2.text.toString(),
                register_username.text.toString(),
                register_phonenumber.text.toString()
            )

        }


    }


    //회원가입 성공시 로그인화면으로 이동
    override fun onRegisterSuccess(message: String) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show()
//        val CompleteRegister = Intent(this, LoginActivity::class.java)
//        startActivity(CompleteRegister)

    }

    //회원가입 실패시 에러 메시지 작동
    override fun onRegisterError(message: String) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun register(UserEmail: String?, UserPwd: String?, UserName: String?, UserPhoneNumber: String?) {
     compositeDisposable.add(registerapi.registerUser(UserEmail,UserPwd,UserName,UserPhoneNumber)
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe({ message ->
             Toast.makeText(this, "회원가입 완료!@###.", Toast.LENGTH_SHORT).show()
         },
         { thr ->
          Log.e("에로메세지",thr.message)
         })
        )

    }
    private fun showProgress(show: Boolean) {
        mProgressView!!.visibility = if (show) View.VISIBLE else View.GONE
    }


}
