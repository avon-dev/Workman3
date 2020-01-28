package com.example.workman.View.Create_Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.workman.R
import kotlinx.android.synthetic.main.activity_create_alarm.*
import java.util.*


class Create_Alarm : AppCompatActivity() {
    val REQUESTCODE_RINGTONE_PICKER: Int = 1000
    var m_strRingToneUri: String? = null
    // 현재 재생중인 링톤
    lateinit var mRtCurrent: Ringtone
    // 문제 해결을 위해 Ringtone으로 변경
    lateinit var mRtm: RingtoneManager

    lateinit var am: AlarmManager
    lateinit var tp: TimePicker
    lateinit var con : Context
    var hour: Int = 0
    var min: Int = 0
    lateinit var pi: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_alarm)
        // 툴바 설정하기
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(true)  // 뒤로가기 버튼 추가.

        mRtm = RingtoneManager(this)


        this.con = this
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        tp = findViewById<TimePicker>(R.id.alarm_timepicker)
        var calendar : Calendar = Calendar.getInstance()
        var myIntent: Intent = Intent(this,AlarmReceiver::class.java)
        button_make_alarm.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    calendar.set(Calendar.HOUR_OF_DAY,tp.hour)
                    calendar.set(Calendar.MINUTE,tp.minute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = tp.hour
                    min = tp.minute
                }else{
                    calendar.set(Calendar.HOUR_OF_DAY,tp.currentHour)
                    calendar.set(Calendar.MINUTE,tp.currentMinute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = tp.currentHour
                    min = tp.currentMinute
                }
                var hr_str: String = hour.toString()
                var min_str: String = min.toString()
                if (hour > 12){
                    hr_str = (hour - 12).toString()
                }
                if (min < 10){
                    min_str = "0$min"
                }
                Toast.makeText(con,"Alarm set to: $hr_str : $min_str",Toast.LENGTH_SHORT).show()
                myIntent.putExtra("extra","on")
                pi = PendingIntent.getBroadcast(this@Create_Alarm,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pi)
                }

            }

        })
        button_cancel_alarm.setOnClickListener{
            Toast.makeText(con,"Alarm off",Toast.LENGTH_SHORT).show()
            pi = PendingIntent.getBroadcast(this@Create_Alarm,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
            am.cancel(pi)
            myIntent.putExtra("extra","off")
            sendBroadcast(myIntent)
        }
        // 원하는 알람을 체크했을때 다른 알람 아이템들 화면에 나오게한다.
        checkBox_Day_of_week_repeat.setOnClickListener {
            if (checkBox_Day_of_week_repeat.isChecked) {
                checkBox_Working_hours_alarm.isChecked = false
                button_Day_of_week.visibility = View.VISIBLE
                spinner_Working_hours_alarm.visibility = View.GONE
            } else {
                button_Day_of_week.visibility = View.GONE
            }

        }
        checkBox_Working_hours_alarm.setOnClickListener {
            if (checkBox_Working_hours_alarm.isChecked) {
                checkBox_Day_of_week_repeat.isChecked = false
                spinner_Working_hours_alarm.visibility = View.VISIBLE
                button_Day_of_week.visibility = View.GONE
            } else {
                spinner_Working_hours_alarm.visibility = View.GONE
            }
        }

        // 근무조에 따라 울리는 알람을 정하는 스피너 셋팅
        val spinner_Alarm = findViewById<Spinner>(R.id.spinner_Working_hours_alarm)
        val spinner_adapter = ArrayAdapter.createFromResource(
            this,
            R.array.Working_hours_alarm,
            android.R.layout.simple_spinner_item
        )

        // 인원수 스피너에 아답터 연결
        spinner_Alarm.adapter = spinner_adapter
        spinner_Alarm.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

        }

        // 알람음 설정.
        alarm_sound.setOnClickListener {
            //-- 기본 알림창을 띄우기 위한 Intent생성
            // -- 기본 알림선택창을 띄우고 결과를 받아와야 하기 때문에 startActivityForResult를 써야한다.
            var intent = Intent(RingtoneManager.ACTION_RINGTONE_PICKER)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "알람음을 설정해주세요.")
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true)
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL)
            //-- 알림 선택창이 떴을 때, 기본값으로 선택되어질 ringtone설정
            if (m_strRingToneUri != null && m_strRingToneUri!!.isEmpty()) {
                intent.putExtra(
                    RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
                    Uri.parse(m_strRingToneUri)
                )
            }
            this.startActivityForResult(intent, REQUESTCODE_RINGTONE_PICKER)

        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


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

    //-- 링톤을 재생하는 함수
    fun startRingtone(uriRingtone: Uri) {
//        this.releaseRingtone()
        try {
//            mRtCurrent = mRtm.getRingtone(0)
            mRtCurrent = RingtoneManager.getRingtone(this, uriRingtone)
            if (mRtCurrent == null) {
                throw Exception("Can't play ringtone")
            }
            alarm_sound.text = mRtCurrent.getTitle(this)
//            mRtCurrent.play()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            Log.e("재생 오류", e.message)
            e.printStackTrace()
        }
    }

    //-- 재생중인 링톤을 중지하는 함수
    fun releaseRingtone() {
        if (mRtCurrent != null) {
            if (mRtCurrent.isPlaying) {
                mRtCurrent.stop()
//                mRtCurrent = null
            }
        }
        alarm_sound.text = ""
    }


    //-- 알림선택창에서 넘어온 데이터를 처리하는 코드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE_RINGTONE_PICKER) {
            if (resultCode == RESULT_OK) {
                // -- 알림음 재생하는 코드 -- //
                var ring = data!!.getParcelableExtra<Uri>(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)

                if (ring != null) {
                    m_strRingToneUri = ring.toString()
                    alarm_sound.text = ring.toString()
                    this.startRingtone(ring)
                } else {
                    m_strRingToneUri = null
                    alarm_sound.text = null
                }
            }
        }
    }

}





