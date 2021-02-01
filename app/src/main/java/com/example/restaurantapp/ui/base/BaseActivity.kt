package com.example.restaurantapp.ui.base

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.restaurantapp.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    val activityScope = CoroutineScope(Dispatchers.Main)
    var navigationBarColorDefault: Int? = null

    fun setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            val transParentColor = ContextCompat.getColor(this, R.color.black_80)

            val systemUiScrim: Int = transParentColor
            var systemUiVisibility = window.decorView.systemUiVisibility
            // Use a dark scrim by default since light status is API 23+
            var statusBarColor = systemUiScrim
            var navigationBarColor = systemUiScrim

            val winParams = window.attributes

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                statusBarColor = Color.TRANSPARENT
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                navigationBarColor = Color.TRANSPARENT
            }
            systemUiVisibility = systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            window.decorView.systemUiVisibility = systemUiVisibility

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                winParams.flags = winParams.flags and
                        (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or
                                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION).inv()
                window.statusBarColor = statusBarColor
                window.navigationBarColor = navigationBarColorDefault ?: navigationBarColor
            }

            window.attributes = winParams
        }
    }
}