package com.example.workman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_employee.*
import kotlinx.android.synthetic.main.activity_group.*
import kotlin.math.E

class EmployeeActivity : AppCompatActivity() {

    lateinit var passiveEmployeeFragment:PassiveEmployeeFragment
    lateinit var activeEmployeeFragment: ActiveEmployeeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        passiveEmployeeFragment = PassiveEmployeeFragment()
        activeEmployeeFragment = ActiveEmployeeFragment()

        supportFragmentManager.beginTransaction().replace(R.id.E_container, activeEmployeeFragment).commit();

        E_passivebutton.setOnClickListener {
            E_passivebutton.background = ContextCompat.getDrawable(this, R.drawable.bottomline)
            E_activebutton.background = null
            supportFragmentManager.beginTransaction().replace(R.id.E_container, passiveEmployeeFragment).commit();
        }
        E_activebutton.setOnClickListener {
            E_activebutton.background = ContextCompat.getDrawable(this, R.drawable.bottomline)
            E_passivebutton.background = null
            supportFragmentManager.beginTransaction().replace(R.id.E_container, activeEmployeeFragment).commit();
        }

        E_fab.setOnClickListener {
            val nextIntent = Intent(this, AddEmployeeActivity::class.java)
            startActivity(nextIntent)
        }
    }
}
