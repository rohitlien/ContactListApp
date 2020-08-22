package com.rohit.phablefragmentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private var mHandler:Handler?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        mHandler = Handler()
        mHandler?.postDelayed(Runnable {
            startMainScreen()
        },1500)
    }

    private fun startMainScreen() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler?.removeCallbacksAndMessages(null)
    }
}