package com.wordpress.lochindev.ramazon_taqvimi.ui.fragment

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.provider.AlarmClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.wordpress.lochindev.ramazon_taqvimi.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView
    private val binding get() = _binding!!
    private lateinit var calendar: android.icu.util.Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    var dateList: ArrayList<String> = ArrayList()
    var timeList: ArrayList<String> = ArrayList()
    var time2List: ArrayList<String> = ArrayList()

    var hour_S: Int = 0
    var minut_S: Int = 0
    var hour_I: Int = 0
    var minut_I: Int = 0

    private lateinit var sharedPreferense: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sdf = SimpleDateFormat("dd")
        val currentDate = sdf.format(Date())

        val cal = Calendar.getInstance()
        val month_date = SimpleDateFormat("MMMM")
        val month_name = month_date.format(cal.time)

//        sharedPreferense = sharedPreferense(
//            "PREFERENCE_NAME",Context.MODE_PRIVATE
//        )

        sharedPreferense =
            requireActivity().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)


        val country = sharedPreferense.getString("country_name", null)

        binding.tvLocationS.text = "O'zbekiston , $country"
        binding.tvLocationI.text = "O'zbekiston , $country"

        sharedPreferense =
            requireActivity().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)


        var index = 0
        when (country) {
            "Andijon viloyat" -> {

                index = -11

            }

            "Buxoro viloyati\"" -> {

                index = 22

            }
            "Jizzax viloyati" -> {

                index = 8

            }
            "Navoiy viloyati" -> {

                index = 18

            }
            "Namangan viloyati" -> {

                index = -9

            }
            "Sirdaryo viloyati" -> {

                index = 4

            }
            "Surxondaryo viloyati" -> {

                index = 13

            }
            "Toshkent viloyati" -> {

                index = 0

            }
            "Toshkent shahri" -> {

                index = 0

            }
            "Farg'ona  viloyati" -> {

                index = -8

            }
            "Xorazm viloyati" -> {

                index = 33

            }
            "Qashqadaryo viloyati" -> {

                index = 17

            }
            "Qoraqalpog'iston" -> {

                index = 39

            }
            "Samarqand viloyati" -> {

                index = 12

            }
        }

        if (month_name.toString().equals("mart") || month_name.toString()
                .equals("March") || month_name.toString().equals("марта")
        ) {


            val date: Int = currentDate.toInt()
            var rd: Int = 0

            if (date >= 23) {

                rd = date - 22
                binding.tvTb.text = ("mart " + currentDate + " / " + rd + " - kun")

            } else {

                rd = 23 - date
                binding.tvTb.text = ("Ramazonga $rd kun qoldi")


            }


        } else if (month_name.toString().equals("aprel") || month_name.toString()
                .equals("April") || month_name.toString().equals("апреля")
        ) {

            val date: Int = currentDate.toInt()
            var rd: Int = 0

            if (date <= 21) {

                rd = date + 9
                binding.tvTb.text = ("aprel " + currentDate + " / " + rd + " - kun")

            } else {

                binding.tvTb.text = "Ramazon taqvimi 2023"

            }


        } else {
            binding.tvTb.text = "Ramazon taqvimi 2023"
        }
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("calendar")

            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                dateList.add(userDetail.getString("date"))
                timeList.add(userDetail.getString("time"))
                time2List.add(userDetail.getString("time2"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val new_date = SimpleDateFormat("dd.MM.yyyy").format(Date()).toString()

        val toCharArray_nd = new_date.toCharArray()

        val mn = toCharArray_nd[0].digitToInt() * 10 + toCharArray_nd[1].digitToInt()

        var nd = 0

        if (mn == 31) nd = 1
        else nd = mn + 1


        toCharArray_nd[0] = (mn / 10).toChar()
        toCharArray_nd[1] = (mn % 10).toChar()


        val d = nd / 10
        val s = nd % 10

        var new_date2 = ""
        if (mn == 31) {
            new_date2 = "$d$s" + "." + toCharArray_nd[3] + "" +
                    (toCharArray_nd[4].digitToInt() + 1) + "." + toCharArray_nd[6] + "" + toCharArray_nd[7] + toCharArray_nd[8] + "" + toCharArray_nd[9]

        } else {
            new_date2 = "$d$s" + "." + toCharArray_nd[3] + "" +
                    toCharArray_nd[4] + "." + toCharArray_nd[6] + "" + toCharArray_nd[7] + toCharArray_nd[8] + "" + toCharArray_nd[9]
        }

        Log.e("td", new_date2)
        for (i in 0 until dateList.size) {


            if (dateList[i] == new_date) {

                val charArray2 = customData(time2List[i], index).toCharArray()
                val aF: Long = toMS(charArray2)
                val charArray = customData(timeList[i], index).toCharArray()
                val aS: Long = toMS(charArray)

                val charArrayT = customData(timeList[i], index - 30).toCharArray()
                val charArray2T = customData(time2List[i], index - 30).toCharArray()

                val aSH = charArrayT[0].digitToInt()
                val bSH = charArrayT[1].digitToInt()

                val aSM = charArrayT[3].digitToInt()
                val bSM = charArrayT[4].digitToInt()
                hour_S = 10 * aSH + bSH
                minut_S = 10 * aSM + bSM

                val aIH = charArray2T[0].digitToInt()
                val bIH = charArray2T[1].digitToInt()

                val aIM = charArray2T[3].digitToInt()
                val bIM = charArray2T[4].digitToInt()
                hour_I = 10 * aIH + bIH
                minut_I = 10 * aIM + bIM


                binding.tvIftrolikTime.text = customData(time2List[i], index)
                binding.tvSaxarlikTime.text = customData(timeList[i], index)
                remaining(aS, aF)


            }

            if (dateList[i] == new_date2) {

                binding.tvSaxarlikTimeTm.text = customData(timeList[i], index)
                binding.tvIftrolikTimeTm.text = customData(time2List[i], index)
                binding.timeTm.text = dateList[i]

            }
        }


        binding.imgSaharlik.setOnClickListener {

            setAlarm("Saharlik vaqti bo'ldi", hour_S, minut_S)


        }

        binding.imgIftorlik.setOnClickListener {

            setAlarm("Iftorlik vaqti bo'ldi", hour_I, minut_I)

        }
        return root
    }


    fun remaining(aS: Long, aF: Long) {
        val new_time = SimpleDateFormat("HH:mm").format(Date()).toString()
        var charArray = new_time.toCharArray()
        val currentTime: Long = toMS(charArray)

        if (aS > currentTime) {
            val ms: Long = aS - currentTime

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvSaharlikRemaining.isCountDown = true
            }
            binding.tvSaharlikRemaining.base = SystemClock.elapsedRealtime() + ms
            binding.tvSaharlikRemaining.start()
        }
        if (aF > currentTime && aS < currentTime) {
            val ms: Long = aF - currentTime

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvIftorlikRemaining.isCountDown = true
            }
            binding.tvIftorlikRemaining.base = SystemClock.elapsedRealtime() + ms
            binding.tvIftorlikRemaining.start()
        }

    }


    private fun loadJSONFromAsset(): String {
        val json: String?
        val assets: AssetManager = requireActivity().assets
        try {
            val inputStream = assets.open("tayyor.json");
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }


    fun setAlarm(message: String, hour: Int, munut: Int) {


        val min = munut
        val hr = hour
        val i = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hr)
            putExtra(AlarmClock.EXTRA_MINUTES, min)


            Log.e("cl", "$hour $munut")


        }
        if (i.resolveActivity(requireActivity().packageManager) != null) {

            startActivity(i)

        }


    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun customData(data: String, index: Int): String {


        var ms: Long = 0


        val charArray = data.toCharArray()

        ms = toMS2(charArray, index)

        val dataFormat = "HH:mm"
        val formatter = SimpleDateFormat(dataFormat, Locale.getDefault())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = android.icu.util.Calendar.getInstance()
            calendar.timeInMillis = ms
        }


        return formatter.format(calendar.time)
    }

    fun toMS2(array: CharArray, index: Int): Long {

        val a = array[0].digitToInt()
        val b = array[1].digitToInt()

        Log.e("BUG", "$a $b")

        val msh: Long = ((a * 10 + b) * 3600000).toLong()
        val m1 = array[3].digitToInt()
        val m2 = array[4].digitToInt() + index

        val msm: Long = ((m1 * 10 + m2) * 60000).toLong()
        Log.e("BUG1", "$m1 $m2")

        return (msh + msm) - 21600000


    }

    fun toMS(array: CharArray): Long {

        val a = array[0].digitToInt()
        val b = array[1].digitToInt()

        Log.e("BUG", "$a $b")

        val msh: Long = ((a * 10 + b) * 3600000).toLong()
        val m1 = array[3].digitToInt()
        val m2 = array[4].digitToInt()

        val msm: Long = ((m1 * 10 + m2) * 60000).toLong()
        Log.e("BUG1", "$m1 $m2")

        return msh + msm


    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}