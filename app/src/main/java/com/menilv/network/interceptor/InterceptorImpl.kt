package com.menilv.network.interceptor

import android.util.Log
import com.menilv.BuildConfig
import com.menilv.network.common.BaseInterceptor
import javax.inject.Inject
import okhttp3.Credentials
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

class InterceptorImpl @Inject constructor() : BaseInterceptor(), Interceptor {
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

            val requestWithHeaders = it.request().newBuilder()
                .addHeader("Authorization", Credentials.basic("9b40eed7b1d14f16ba3abfad216167e8", "kXatSEqBrGIaHeCp"))
                .build()
            it.proceed(requestWithHeaders)
        }
    }

    override fun getApplicationInterceptors() =
        listOf(
            headerInterceptor(),
            loggingInterceptor(),
        )
}
