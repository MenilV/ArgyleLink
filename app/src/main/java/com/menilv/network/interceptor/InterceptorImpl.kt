package com.menilv.network.interceptor

import android.os.Build
import android.util.Log
import com.menilv.BuildConfig
import com.menilv.network.common.BaseInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import okhttp3.CacheControl
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

class InterceptorImpl @Inject constructor() : BaseInterceptor(), Interceptor {
    override var token: String = ""
        get() = field

    override fun loggingInterceptor(): okhttp3.Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            if (BuildConfig.DEBUG) {
                Log.d("API", message)
            }
        }
        loggingInterceptor.level = BODY
        return loggingInterceptor
    }

    override fun headerInterceptor(): okhttp3.Interceptor {
        return okhttp3.Interceptor {
            val userAgent = System.getProperty("http.agent") ?: ""

            val requestWithHeaders = it.request().newBuilder()
                .addHeader("os_version", Build.VERSION.RELEASE)
                .addHeader("app_version", BuildConfig.VERSION_NAME)
                .addHeader("device_model", Build.MANUFACTURER + " " + Build.MODEL)
                .header("user-agent", userAgent)
                .build()
            it.proceed(requestWithHeaders)
        }
    }

    override fun cacheInterceptor(): okhttp3.Interceptor {
        return okhttp3.Interceptor {
            val cacheControl: CacheControl = CacheControl.Builder()
                .onlyIfCached()
                .maxAge(15, TimeUnit.MINUTES) // 15 minutes cache
                .build()

            val response = it.proceed(it.request())
            return@Interceptor response.newBuilder()
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }

    override fun getApplicationInterceptors() =
        listOf(
            requestInterceptor(),
            responseInterceptor(),
            loggingInterceptor(),
            cacheInterceptor()
        )
}
