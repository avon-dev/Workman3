package com.example.workman.View.Create_Alarm

import Alarm
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.os.IBinder
import android.provider.Settings
import com.example.workman.R

class AlarmService: Service(){
    companion object {
        lateinit var r: Ringtone
    }
    private var mediaPlayer : MediaPlayer? = null
    private lateinit var alarm :Alarm
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val onOff = intent?.extras?.getString("ON_OFF")

        val ADD_INTENT = getString(R.string.add_intent)
        val OFF_INTENT = getString(R.string.off_intent)

        when(onOff){
            ADD_INTENT->{
                val uri = Settings.System.DEFAULT_ALARM_ALERT_URI
                mediaPlayer = MediaPlayer.create(this, uri)
                mediaPlayer?.start()
//                var URi = Uri.parse(alarm.melody)
//                r = RingtoneManager.getRingtone(this,URi)
//                r.play()
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