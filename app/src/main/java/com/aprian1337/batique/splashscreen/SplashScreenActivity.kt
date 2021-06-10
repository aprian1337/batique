package com.aprian1337.batique.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.aprian1337.batique.MainActivity
import com.aprian1337.batique.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val timeSplashScreen = 4000L
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this@SplashScreenActivity, MainActivity::class.java).apply {
                startActivity(this)
            }
        }, timeSplashScreen)
    }
}