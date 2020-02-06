package com.example.workman

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.workman.View.Alarm.AlarmFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 프래그먼트들의 베이스인 MainActivity
 */
class MainActivity : AppCompatActivity() {

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {

        var selectedFragment: Fragment? = null

        when(it.itemId) {
            R.id.navigation_home -> selectedFragment = HomeFragment()
            R.id.navigation_schedule -> selectedFragment = ScheduleFragment()
            R.id.navigation_time -> selectedFragment = TimeFragment()
            R.id.navigation_alarm -> selectedFragment =
                AlarmFragment()
            R.id.navigation_menu -> selectedFragment = MenuFragment()
        }

        selectedFragment?.let { it1 ->
            supportFragmentManager.beginTransaction().replace(R.id.container,
                it1
            ).commit()
        }

        true

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 툴바 타이틀 변경하기.
        toolbar.title = "홈"
        toolbar.setTitleTextColor(Color.WHITE)


        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container,
                HomeFragment()).commit()
        }

    }
}
