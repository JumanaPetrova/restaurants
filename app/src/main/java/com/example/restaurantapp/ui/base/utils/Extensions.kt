package com.example.restaurantapp.ui.base.utils

import android.os.Build
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T =
    ViewModelProvider(this, factory)[T::class.java]

fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.addCircleRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}