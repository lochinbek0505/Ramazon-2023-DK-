package com.wordpress.lochindev.ramazon_taqvimi.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.wordpress.lochindev.ramazon_taqvimi.adapter.AdapterCalendar
import com.wordpress.lochindev.ramazon_taqvimi.databinding.FragmentCalendarBinding
import com.wordpress.lochindev.ramazon_taqvimi.models.DataCalendar
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private lateinit var sharedPreferense: SharedPreferences

    private val binding get() = _binding!!
    val dataList = ArrayList<DataCalendar>()
    private lateinit var adapter: AdapterCalendar
    private lateinit var calendar: Calendar
    private var country: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = AdapterCalendar(dataList)
        binding.rv.adapter = adapter
        sharedPreferense =
            requireActivity().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)


        country = sharedPreferense.getString("country_name", null).toString()
        var index=0
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
            "Buxoro viloyati" ->{
                index=22
            }
            "Samarqand viloyati" -> {

                index = 12

            }
        }
        loadRv(index)

        return root
    }

    private fun loadRv(index: Int) {
        val listdate: ArrayList<String> = ArrayList()
        val listSaharlikTime: ArrayList<String> = ArrayList()
        val listIftorlikTime: ArrayList<String> = ArrayList()
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("calendar")

            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                listdate.add(userDetail.getString("date"))
                listSaharlikTime.add(userDetail.getString("time"))
                listIftorlikTime.add(userDetail.getString("time2"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        for (i in 0 until listdate.size) {

            val sx = customData(listSaharlikTime[i],index)
            val iff = customData(listIftorlikTime[i],index)

            dataList.add(
                DataCalendar(
                    listdate[i],
                    sx,
                    iff
                )
            )
        }

    }


    fun customData(data: String, index:Int): String {


//        var index = 5

        var ms: Long = 0
//        "Andijon viloyat
////        "Buxoro viloyati",
////        "Jizzax viloyati",
////        "Navoiy viloyati",
////        "Namangan viloyati",
////        "Samarqand viloyati",
////        "Sirdaryo viloyati",
////        "Surxondaryo viloyati",
////        "Toshkent viloyati",
////        "Toshkent shahri",
////        "Farg'ona  viloyati",
////        "Xorazm viloyati",
////        "Qashqadaryo viloyati",
////        "Qoraqalpog'iston "i",
        val charArray = data.toCharArray()


        ms = toMS(charArray, index)

        val dataFormat = "HH:mm"
        val formatter = SimpleDateFormat(dataFormat, Locale.getDefault())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            calendar = Calendar.getInstance()
            calendar.timeInMillis = ms
        }



        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            formatter.format(calendar.time)
        } else {
            TODO("VERSION.SDK_INT < N")
        }
    }

    fun toMS(array: CharArray, index: Int): Long {

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}