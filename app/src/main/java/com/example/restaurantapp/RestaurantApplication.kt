package com.example.restaurantapp

import android.app.Activity
import com.example.restaurantapp.injection.component.DaggerAppComponent
import com.example.restaurantapp.injection.component.applyAutoInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

class RestaurantApplication : DaggerApplication(), HasActivityInjector {
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
    }

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun activityInjector() = dispatchingAndroidInjector

}