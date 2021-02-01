package com.example.restaurantapp.ui.base.utils

import android.app.Activity
import android.content.Intent
import com.example.restaurantapp.ui.MainActivity


object IntentHelper {

    fun goToMainScreenActivity(context: Activity?) {
        val intent = Intent(
            context,
            MainActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.apply {
            startActivity(
                intent
            )
            finish()
        }
    }
}