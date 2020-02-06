package com.example.workman.View.Create_Alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.workman.R

class AlarmReceiver : BroadcastReceiver() {
    // this hold pendingIntend id if one pendingIntend trigger. The PendingIntent'id is alarm'id
    companion object {
        var pendingId: Int = 0
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent != null && context != null) {
            val intentToService = Intent(context, AlarmService::class.java)
            val intentType = intent.extras?.getString("intentType")

            val ADD_INTENT = context.getString(R.string.add_intent)
            val OFF_INTENT = context.getString(R.string.off_intent)

            when (intentType) {
                ADD_INTENT -> {
                    var alarm_melody = intent.extras?.getString("alarm_melody")
                    pendingId = intent.extras?.getInt("PendingId") as Int
                    intentToService.putExtra("ON_OFF", ADD_INTENT)
                    intentToService.putExtra("alarm_melody",alarm_melody)
                    context.startService(intentToService)
                }
                OFF_INTENT -> {
                    val alarmId = intent.extras?.getInt("AlarmId")
                    // sending to AlarmService
                    intentToService.putExtra("ON_OFF", OFF_INTENT)
                    intentToService.putExtra("AlarmId", alarmId)
                    context.startService(intentToService)
                }
            }
        }
    }
}

//    override fun onReceive(context: Context?, intent: Intent?) {
//
//        var getResult: String = intent!!.getStringExtra("extra")
//        var service_intent : Intent = Intent(context,RingtoneService::class.java)
//        service_intent.putExtra("extra",getResult)
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
//            context!!.startForegroundService(service_intent)
//        }else{
//            context!!.startService(service_intent)
//        }
//
//    }
//
//}