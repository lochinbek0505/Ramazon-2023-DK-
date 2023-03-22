package com.wordpress.lochindev.ramazon_taqvimi.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.wordpress.lochindev.ramazon_taqvimi.R
import com.wordpress.lochindev.ramazon_taqvimi.databinding.ActivityStartBinding
import com.wordpress.lochindev.ramazon_taqvimi.ui.activity.MainActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityStartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val window = window;
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500);
        window.navigationBarColor = ContextCompat.getColor(this, R.color.purple_500);

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val country = sharedPreference.getString("country_name", null)

        if (country != null) {
            val intent = Intent(this, HomeActivity::class.java);
            startActivity(intent)
            finish()
        } else {
            binding.cardView.setOnClickListener {
                val intent = Intent(this, LocationActivity::class.java);
                startActivity(intent)
                finish()
            }
        }

    }
}