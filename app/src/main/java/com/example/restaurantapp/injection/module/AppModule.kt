package com.example.restaurantapp.injection.module

import android.app.Application
import com.baraka.android.injection.qualifiers.ApplicationContext
import com.example.restaurantapp.core.api.RestService
import com.example.restaurantapp.core.api.RetrofitHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * AppModule
 *
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideAppContext(application: Application): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun provideRetrofitHelper(
    ): RetrofitHelper {
        return RetrofitHelper()
    }

    @Singleton
    @Provides
    fun provideRpcService(
        retrofitHelper: RetrofitHelper
    ): RestService {
        return retrofitHelper.newRestService()
    }
}