package com.example.restaurantapp.core.api

import com.example.restaurantapp.BuildConfig
import com.example.restaurantapp.core.api.adapters.GsonBooleanAdapter
import com.example.restaurantapp.core.api.adapters.GsonUTCDateAdapter
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Хелпер ретрофит сервиса
 *
 */
class RetrofitHelper @Inject constructor() {
    /**
     * Создание rest сервиса
     *
     * @return Rest сервис
     */
    fun newRestService(): RestService {
        return restService ?: OkHttpClient.Builder().let { httpClient ->
            httpClient.connectTimeout(
                TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )
            httpClient.readTimeout(
                TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )
            httpClient.writeTimeout(
                TIMEOUT.toLong(),
                TimeUnit.SECONDS
            )
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(logging)
            }
            val client = httpClient.build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.REST_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            retrofit.create(
                RestService::class.java
            )
        }.apply {
            restService = this
        }
    }

    companion object {
        private const val TIMEOUT = 120
        private var restService: RestService? = null


        private val gson: Gson
            get() = GsonBuilder().addSerializationExclusionStrategy(object :
                ExclusionStrategy {
                override fun shouldSkipField(f: FieldAttributes): Boolean {
                    val t = f.getAnnotation(IgnoreJson::class.java)
                    return t != null
                }

                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }
            }).addDeserializationExclusionStrategy(object : ExclusionStrategy {
                override fun shouldSkipField(f: FieldAttributes): Boolean {
                    val t = f.getAnnotation(IgnoreJson::class.java)
                    return t != null
                }

                override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                    return false
                }
            })
                .registerTypeAdapter(
                    Date::class.java,
                    GsonUTCDateAdapter()
                )
                .registerTypeAdapter(
                    Boolean::class.java,
                    GsonBooleanAdapter()
                )
                .serializeNulls()
                .create()
    }
}