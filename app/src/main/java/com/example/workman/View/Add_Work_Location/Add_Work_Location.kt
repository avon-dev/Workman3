package com.example.workman.View.Add_Work_Location

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.workman.MainActivity
import com.example.workman.R
import com.example.workman.View.Select_Company.Select_Company
import kotlinx.android.synthetic.main.activity_add_work_location.*

class Add_Work_Location : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_work_location)
        // 툴바 설정하기
        val toolbar = findViewById(R.id.add_work_location_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)  // 뒤로가기 버튼 추가.

        // 취소 버튼눌렀을때 지금 화면은 사라지고 회사 선택화면으로 돌아간다.
        button_cancel.setOnClickListener {
            val intent = Intent(this, Select_Company::class.java)
            startActivity(intent)
            finish()
        }

        // 생성하기 버튼을 눌렀을때 회사가 생성이 되고 메인 액티비티 화면으로 이동한다.
        button_create_company.setOnClickListener {
            val intent = Intent(this, Select_Company::class.java)
            startActivity(intent)
            finish()
        }
    }

    // 툴바 뒤로가기 버튼 설정
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
