package com.example.workman.View.Create_Company

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.workman.R
import com.example.workman.View.Add_Work_Location.Add_Work_Location
import com.example.workman.View.Select_Company.Select_Company
import kotlinx.android.synthetic.main.activity_create_company.*

class Create_Company : AppCompatActivity() {
    private var company_name : String? = null
    private var people_num: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_company)
        // 툴바 설정하기
        val toolbar = findViewById(R.id.create_company_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)  // 뒤로가기 버튼 추가.

        // 회사 인원수를 정하는 스피너 셋팅
        val spinner_personnel = findViewById<Spinner>(R.id.spinner_personnel)
        val spinner_adapter = ArrayAdapter.createFromResource(this, R.array.Number_of_people,android.R.layout.simple_spinner_item)

        // 인원수 스피너에 아답터 연결
        spinner_personnel.adapter = spinner_adapter
        spinner_personnel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager   // 버튼 클릭시 EditText 키보드 내리기.
                inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)   // 버튼 클릭시 EditText 키보드 내리기.
                when(position){  // 스피너 선택값에 따른 회사 인원수 타입
                    0 -> people_num = null
                    1 -> people_num = 1 // 1 ~ 20 명
                    2 -> people_num = 2 // 21 ~ 50 명
                    3 -> people_num = 3 // 51 ~ 100 명
                    4 -> people_num = 4 // 101 ~ 300 명
                    5 -> people_num = 5 // 301 ~ 500 명
                    6 -> people_num = 6 // 501 ~ 이상
                    else -> people_num = null
                }
            }

        }

        // 취소 버튼 눌렀을떄 회사 선택화면으로 이동하고 지금 화면은 없어진다.
        button_cancel.setOnClickListener {
            val intent = Intent(this, Select_Company::class.java)
            startActivity(intent)
            finish()
        }

        // 다음 버튼 눌렀을때 직장위치 추가하기 화면으로 이동한다.
        button_next.setOnClickListener {
            if (editText_Company_Name.text.isEmpty()){
                Toast.makeText(this,"회사명을 입력해주세요.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (people_num == null){
                Toast.makeText(this,"회사인원수를 선택해주세요.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else {
                company_name = editText_Company_Name.text.toString()
                val intent = Intent(this, Add_Work_Location::class.java)
                intent.putExtra("company_name",company_name)
                intent.putExtra("people_num",people_num.toString())
                Log.d("company_name",company_name)
                Log.d("people_num",people_num.toString())
                startActivity(intent)
            }

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
