package com.example.workman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_schedule_group_set1.*

class ScheduleGroupSet1 : AppCompatActivity() {

    // 스피너 리스트 담을 스트링 변수
    var R_U_spinner1:String = ""
    var j : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_group_set1)

        val colors = arrayOf("근무조 설정","1","2","3","4","5","직접입력")
        // Initializing an ArrayAdapter spinner
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            colors // Array
        )
        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        // Finally, data bind the spinner object with dapter
        schedule_group_set1_spinner.adapter = adapter;
        //schedule_group_set1_spinner.setSelection(0)
        schedule_group_set1_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                // Display the selected item text on text view
                // text_view.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
                R_U_spinner1 = parent.getItemAtPosition(position).toString()
                j = position

            }
            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
                j = 7
            }
        }

        schedule_group_set1_next.setOnClickListener {
            val nextIntent = Intent(this, ScheduleGroupSet2::class.java)
            nextIntent.putExtra("Gnumber",j)
            //Toast.makeText(this@ScheduleGroupSet1, "put "+j, Toast.LENGTH_LONG).show()
            startActivity(nextIntent)
        }
    }
}
