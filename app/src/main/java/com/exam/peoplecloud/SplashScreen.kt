package com.exam.peoplecloud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.exam.peoplecloud.databinding.ActivitySplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

       window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
           WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        binding.textViewHeader.startAnimation(slideAnimation)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}