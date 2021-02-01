package com.example.restaurantapp.ui.base.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.roundToInt

object ViewUtil {
    fun dpToPx(dp: Int): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).roundToInt()
    }

    /**
     * Скрыть клавиатуру
     */
    fun hideKeyboard(activity: Activity?) {
        activity?.let {
            hideKeyboard(activity.currentFocus)
        }
    }

    /**
     * Скрыть клавиатуру
     */
    fun hideKeyboard(view: View?) {
        view?.let { v ->
            val inputMethodManager =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
            inputMethodManager?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}