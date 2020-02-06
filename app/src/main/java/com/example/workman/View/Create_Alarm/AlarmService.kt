package com.example.workman.View.Create_Alarm

import Alarm
import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.workman.R
import com.example.workman.View.Alarm_stop

class AlarmService: Service(){
    private var r : Ringtone? = null
    private var mediaPlayer : MediaPlayer? = null
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val onOff = intent?.extras?.getString("ON_OFF")

        val ADD_INTENT = getString(R.string.add_intent)
        val OFF_INTENT = getString(R.string.off_intent)
        var alarm_melody = intent?.extras?.getString("alarm_melody")
        when(onOff){
            ADD_INTENT->{
//                val uri = Settings.System.DEFAULT_ALARM_ALERT_URI
//                mediaPlayer = MediaPlayer.create(this, uri)
//                mediaPlayer?.start()
//                var stop_activity_intent : Intent = Intent(this,Alarm_stop::class.java)
//                var pi : PendingIntent = PendingIntent.getActivity(this,0,stop_activity_intent,0)
//                val CHANNEL_ID = "default"
//                val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    NotificationChannel(
//                        CHANNEL_ID,
//                        "Channel human readable title",
//                        NotificationManager.IMPORTANCE_DEFAULT
//                    )
//                } else {
//                    TODO("VERSION.SDK_INT < O")
//                }
//
//                (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
//                    channel
//                )
//                var notification : Notification = NotificationCompat.Builder(this, CHANNEL_ID)
//                    .setContentTitle("Alarm is going off")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentText("Click Me")
//                    .setContentIntent(pi)
//                    .setAutoCancel(true)
//                    .build()
//                startForeground(1,notification)
                var URi = Uri.parse(alarm_melody)
                r = RingtoneManager.getRingtone(this,URi)
                Log.d("Alarm_Ringtone",r?.getTitle(this).toString())
                Log.d("Alarm_Ringtone",alarm_melody)
                r?.play()
                // TODO add alarm notification screen with cancel button
            }
            OFF_INTENT->{
                val alarmId = intent?.extras?.getInt("AlarmId")
                if ( mediaPlayer!!.isPlaying && alarmId == AlarmReceiver.pendingId) {
                    mediaPlayer?.stop()
                    mediaPlayer?.reset()
                    //TODO maybe need set next alarm here ?
                }
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.reset()
    }
}