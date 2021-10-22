package com.example.artflowersapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.ActivitySplashScreenBinding
import com.example.artflowersapp.utils.FakeData
import com.example.artflowersapp.viewModel.SplashVM

class SplashScreenActivity : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding? = null
    private val binding: ActivitySplashScreenBinding get() = _binding!!
    private val viewModel: SplashVM by viewModels()
    // After 3000 mileSeconds / 3 seconds your next activity will display.
    private var TIME_OUT:Long = 3000
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences("com.example.artflowersapp", Context.MODE_PRIVATE)
        if (!prefs.getBoolean("IS_FIRST_LAUNCH", false)) {
            viewModel.insertAll(FakeData.flowers)
        }
        loadSplashScreen()
    }

    private fun loadSplashScreen(){
        Handler().postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            prefs.edit {
                putBoolean("IS_FIRST_LAUNCH", true)
            }
            finish()
        },TIME_OUT)
    }

}