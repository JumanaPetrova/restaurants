package com.example.restaurantapp.ui

import android.os.Bundle
import com.example.restaurantapp.R
import com.example.restaurantapp.ui.base.BaseActivity
import com.example.restaurantapp.ui.base.utils.IntentHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SplashScreenActivity : BaseActivity() {

    companion object {
        const val SPLASH_WAIT_SECONDS = 2L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setFullScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityScope.launch {
            delay(TimeUnit.SECONDS.toMillis(SPLASH_WAIT_SECONDS))
            IntentHelper.goToMainScreenActivity(this@SplashScreenActivity)
        }
    }
}