package com.wordpress.lochindev.ramazon_taqvimi.utilits

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.wordpress.lochindev.ramazon_taqvimi.R
import com.wordpress.lochindev.ramazon_taqvimi.ui.activity.MainActivity

class NotificationUtils(base: Context) : ContextWrapper(base) {
    val MYCHANNEL_ID = "App Alert Notification ID"
    val MYCHANNEL_NAME = "App Alert Notification"
    private var manager: NotificationManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        ) {
            createChannels()
        }
    }

    // Create channel for Android version 26+
    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel =
            NotificationChannel(
                MYCHANNEL_ID,
                MYCHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
        channel.enableVibration(true)
        getManager().createNotificationChannel(channel)
    }

    // Get Manager
    fun getManager(): NotificationManager {
        if (manager == null) manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    fun getNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val alarmSound:Uri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val vibrate: LongArray = longArrayOf(0, 500, 1000)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        return NotificationCompat.Builder(
            applicationContext,
            MYCHANNEL_ID
        ).setContentTitle("Alarm!")
            .setContentText("Your AlarmManager is working.")
            .setSmallIcon(R.drawable.ic_icon_sound)
            .setVibrate(vibrate)
            .setDefaults(Notification.DEFAULT_LIGHTS)
            .setColor(Color.YELLOW).setContentIntent(pendingIntent)
            .setSound(
                alarmSound
//                Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + applicationContext.resources + "/" + R.raw.iftorlik)
            )
            .setAutoCancel(true)
    }
}