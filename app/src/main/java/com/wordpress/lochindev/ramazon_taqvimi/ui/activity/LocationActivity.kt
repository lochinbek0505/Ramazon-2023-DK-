package com.wordpress.lochindev.ramazon_taqvimi.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wordpress.lochindev.ramazon_taqvimi.R
import com.wordpress.lochindev.ramazon_taqvimi.adapter.AdapterCountry
import com.wordpress.lochindev.ramazon_taqvimi.databinding.ActivityLocation2Binding

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocation2Binding
    var back = 0


    private lateinit var sharedPreferense: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLocation2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        back = intent.getIntExtra("BACK", 0)

        sharedPreferense = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val array: ArrayList<String> = arrayListOf(

            "Andijon viloyati",
            "Buxoro viloyati",
            "Jizzax viloyati",
            "Navoiy viloyati",
            "Namangan viloyati",
            "Samarqand viloyati",
            "Sirdaryo viloyati",
            "Surxondaryo viloyati",
            "Toshkent viloyati",
            "Toshkent shahri",
            "Farg'ona  viloyati",
            "Xorazm viloyati",
            "Qashqadaryo viloyati",
            "Qoraqalpog'iston"

        )

        val adapter = AdapterCountry(array, object : AdapterCountry.ItemSelectListener {
            override fun onClick(id: String) {

                val editor = sharedPreferense.edit()

                editor.putString("country_name", id)

                editor.apply()

                startActivity(Intent(this@LocationActivity, HomeActivity::class.java))
                finish()

            }
        })

        binding.rvLacation.adapter = adapter

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (back == 1) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

    }
}