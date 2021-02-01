package com.example.restaurantapp.injection.component

import android.app.Application
import com.example.restaurantapp.RestaurantApplication
import com.example.restaurantapp.injection.module.ActivityModule
import com.example.restaurantapp.injection.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * AppComponent
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<RestaurantApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: RestaurantApplication)
}