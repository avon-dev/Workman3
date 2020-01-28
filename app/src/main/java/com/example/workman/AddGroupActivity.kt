package com.example.workman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workman.Adapter.ItemData
import com.example.workman.Adapter.SpinnerAdapter
import kotlinx.android.synthetic.main.activity_add_group.*

class AddGroupActivity : AppCompatActivity() {

    lateinit var spinnerAdapter:SpinnerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_group)

        var list:ArrayList<ItemData> = ArrayList()
        list.add(ItemData(R.color.black,"검정색"))
        list.add(ItemData(R.color.blue,"파란색"))
        list.add(ItemData(R.color.red,"빨간색"))
        list.add(ItemData(R.color.green,"초록색"))
        list.add(ItemData(R.color.gray,"회색"))
        list.add(ItemData(R.color.mint,"민트색"))

        spinnerAdapter = SpinnerAdapter(this,R.layout.spinner,list)
        groupmarker.adapter = spinnerAdapter
    }
}
