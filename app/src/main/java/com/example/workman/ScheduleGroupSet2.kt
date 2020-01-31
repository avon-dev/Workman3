package com.example.workman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.workman.Common.Common
import kotlinx.android.synthetic.main.activity_schedule_group_set2.*

class ScheduleGroupSet2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_group_set2)

        val Num = intent.getIntExtra("Gnumber",0)

        schedule_group_set2_groupNumber.setText(Num.toString())

        // 작성하기 버튼 클릭
        schedule_group_set2_groupSet.setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            nextIntent.putExtra("Fragment",2)
            Common.selected_Group_Number = Num
            //Toast.makeText(this@ScheduleGroupSet1, "put "+j, Toast.LENGTH_LONG).show()
            startActivity(nextIntent)
        }
    }
}
