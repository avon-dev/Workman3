package com.example.workman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        G_fab.setOnClickListener {
            val nextIntent = Intent(this, AddGroupActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
