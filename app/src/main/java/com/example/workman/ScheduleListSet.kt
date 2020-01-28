package com.example.workman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.recyclerview.widget.OrientationHelper
import com.applikeysolutions.cosmocalendar.settings.lists.connected_days.ConnectedDays
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import kotlinx.android.synthetic.main.activity_schedule_list_set.*
import java.util.*

class ScheduleListSet : AppCompatActivity() {

    // 스피너 리스트 담을 스트링 변수
    var R_U_spinner1:String = ""
    var j : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_list_set)

        ListSet_calendar_view.calendarOrientation = OrientationHelper.HORIZONTAL

        // 날짜 선택타입 설정
        radioButton_type.setOnCheckedChangeListener { group, checkedId ->
            if(group.id == R.id.radioButton_type) {
                when(checkedId) {
                    R.id.radioButton_multi -> {
                        ListSet_calendar_view.selectionType = SelectionType.MULTIPLE
                        return@setOnCheckedChangeListener
                    }
                    R.id.radioButton_range -> {
                        ListSet_calendar_view.selectionType = SelectionType.RANGE
                        return@setOnCheckedChangeListener
                    }
                }
            }
        }

        // 스피너 설정
        val colors = arrayOf("조설정","A","B","C","D")
        // Initializing an ArrayAdapter spinner
        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            colors // Array
        )
        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        // Finally, data bind the spinner object with dapter
        schedule_List_set_spinner1.adapter = adapter;
        schedule_List_set_spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
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

    }
}
