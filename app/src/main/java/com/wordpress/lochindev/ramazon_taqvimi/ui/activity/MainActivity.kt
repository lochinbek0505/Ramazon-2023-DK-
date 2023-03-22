package com.wordpress.lochindev.ramazon_taqvimi.ui.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.content.getSystemService
import com.wordpress.lochindev.ramazon_taqvimi.R
import com.wordpress.lochindev.ramazon_taqvimi.utilits.AlarmReceiver
import com.wordpress.lochindev.ramazon_taqvimi.utilits.TimePickerFragment

class MainActivity : AppCompatActivity() {
    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
    lateinit var btnSetAlarm: Button
    lateinit var timePicker: TimePicker
    override fun onCreate(
        savedInstanceState:
        Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"
        timePicker = findViewById(R.id.timePicker)
        btnSetAlarm = findViewById(R.id.toggleButton)

        btnSetAlarm.setOnClickListener {

            val intent=Intent(AlarmClock.ACTION_SET_ALARM).apply{

                putExtra(AlarmClock.EXTRA_MESSAGE,"test")
                putExtra(AlarmClock.EXTRA_HOUR,5)
                putExtra(AlarmClock.EXTRA_MINUTES,40)


            }

            if(intent.resolveActivity(packageManager)!=null){

                startActivity(intent)

            }
            else{
                Toast.makeText(this,"ERRRRRROOROORORO",Toast.LENGTH_LONG).show()
            }


        }


//        val intent2= Intent(AlarmClock.ACTION_SET_ALARM)
//        intent2.putExtra(AlarmClock.EXTRA_SKIP_UI,true)
//        intent2.putExtra(AlarmClock.EXTRA_MESSAGE,"TESR ALARM")
//        intent2.putExtra(AlarmClock.EXTRA_HOUR,18)
//        intent2.putExtra(AlarmClock.EXTRA_MINUTES,40)
//        startActivity(intent)

//        btnSetAlarm.setOnClickListener {
//            val calendar: Calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                Calendar.getInstance()
//            } else {
//                TODO("VERSION.SDK_INT < N")
//            }
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    calendar.set(
//                        calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH),
//                        calendar.get(Calendar.DAY_OF_MONTH),
//                        timePicker.hour, timePicker.minute, 0
//                    )
//                } else {
//                    TODO("VERSION.SDK_INT < N")
//                }
//            } else {
//                calendar.set(
//                    calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH),
//                    timePicker.currentHour, timePicker.currentMinute, 0
//                )
//            }
////            setAlarm(calendar.timeInMillis)
//        }
    }

//    private fun setAlarm(timeInMillis: Long) {
//            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            val intent = Intent(
//                this,
//                MyAlarm::class.java
//            )
//            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//            alarmManager.setRepeating(
//                AlarmManager.RTC,
//                timeInMillis, AlarmManager.INTERVAL_DAY,
//                pendingIntent
//            )
//        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
//        }
    private class MyAlarm : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Log.d("Alarm Bell", "Alarm just fired")
            }
        }


fun showTimePickerFragment(view: View) {
//
//        val timePickerFragment = TimePickerFragment()
//        timePickerFragment.show(supportFragmentManager, "time_picker")
//    }
//
//    override fun onTimeSet(time_Picker: TimePicker?, hour: Int, minute: Int) {
//
//
//        val calendar = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Calendar.getInstance()
//        } else {
//
//        }
//        calendar.set(Calendar.HOUR_OF_DAY, hour)
//        calendar.set(Calendar.MINUTE, minute)
//        calendar.set(Calendar.SECOND, 0)
//        startAlarm(calendar)
//
//    }
//
//    private fun startAlarm(calendar: Calendar?) {
//
//        val alarmManeger = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//        val intent = Intent(this, AlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            if (calendar != null) {
//                alarmManeger.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
//            }
//        }
//
//
//    }
}
    fun scheduleNotification(id:Int, name:String,delay:Long){

        val notification= Intent(this,AlarmReceiver::class.java)
        notification.putExtra("ID",id)
        notification.putExtra("Name",name)

        val pendingIntent=PendingIntent.getBroadcast(this,id,notification,PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmMeneger:AlarmManager=getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val futureMillisekunds:Long=SystemClock.elapsedRealtime()+delay

        alarmMeneger.set(AlarmManager.ELAPSED_REALTIME,futureMillisekunds,pendingIntent)





    }


}