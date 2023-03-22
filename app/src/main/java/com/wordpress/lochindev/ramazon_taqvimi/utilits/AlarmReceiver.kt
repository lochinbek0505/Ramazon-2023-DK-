package com.wordpress.lochindev.ramazon_taqvimi.utilits

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.wordpress.lochindev.ramazon_taqvimi.R
import com.wordpress.lochindev.ramazon_taqvimi.ui.activity.MainActivity

class AlarmReceiver : BroadcastReceiver() {

    val CHANNEL_ID = "dasturchilarKlubi"
    private lateinit var context: Context
    private lateinit var notificationManager:NotificationManager

    override fun onReceive(context: Context?, intent: Intent?) {

        this.context = context!!

        notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val i = Intent(context, MainActivity::class.java)
        i!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK



//        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

//        createNotification()

//        val builder = NotificationCompat.Builder(context!!, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_icon_sound)
//            .setContentTitle("Title")
//            .setContentText("TEXT")
//            .setAutoCancel(true)
//            .setSound(
//                RingtoneManager.getDefaultUri(
//                    RingtoneManager
//                        .TYPE_NOTIFICATION
//                ) ).setAutoCancel(true)
//                    .setDefaults(NotificationCompat.DEFAULT_ALL)
//                    .setPriority(NotificationCompat.PRIORITY_HIGH)
//                    .setContentIntent(pendingIntent)

//        notificationManager.notify(123,builder.build())
//                val notifyManager = NotificationManagerCompat.from(context)
//        notifyManager.notify(123, builder.build())
        val notificationUtilits = NotificationUtils(context!!)
        val notification = notificationUtilits.getNotificationBuilder().build()
        notificationUtilits.getManager().notify(150, notification)
    }

    private fun createNotification() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Notification"
            val description = "Schedule Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChanel = NotificationChannel(CHANNEL_ID, name, importance)
//                notificationChanel.description(description)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChanel)


        }


    }
}