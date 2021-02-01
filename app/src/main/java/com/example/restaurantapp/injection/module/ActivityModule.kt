@file:Suppress("unused", "unused")

package com.example.restaurantapp.injection.module


import com.baraka.android.injection.scope.PerActivity
import com.example.restaurantapp.ui.MainActivity
import com.example.restaurantapp.ui.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * ActivityModule
 *
 */
@Module
abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun contributeSplashScreenActivity(): SplashScreenActivity
}