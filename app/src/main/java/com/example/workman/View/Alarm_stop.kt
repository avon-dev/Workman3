package com.example.workman.View

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.workman.MainActivity
import com.example.workman.R
import kotlinx.android.synthetic.main.activity_alarm_stop.*

class Alarm_stop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        setContentView(R.layout.activity_alarm_stop)






        seekBar2.setOnSeekBarChangeListener(Seekbarchage())


    }

    inner class Seekbarchage : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (seekBar?.progress!! >= 90) {
                var intent = Intent(this@Alarm_stop, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            seekBar?.progress = 0
        }

    }

}



