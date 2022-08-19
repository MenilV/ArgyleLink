package com.menilv.common

import com.menilv.network.interceptor.Interceptor
import com.menilv.network.repository.http.HttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import okhttp3.OkHttpClient

class TestHttpClientImpl @Inject constructor(
    private val interceptor: Interceptor
) : HttpClient {
    override fun getClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(TimeoutValues.CONNECT_TIMEOUT.timeout, TimeUnit.SECONDS)
            .writeTimeout(TimeoutValues.WRITE_TIMEOUT.timeout, TimeUnit.SECONDS)
            .readTimeout(TimeoutValues.READ_TIMEOUT.timeout, TimeUnit.SECONDS)

        interceptor.getApplicationInterceptors()
            .forEach {
                okHttpClientBuilder.addInterceptor(it)
            }

        return okHttpClientBuilder.build()
    }

    enum class TimeoutValues(val timeout: Long) {
        CONNECT_TIMEOUT(60),
        READ_TIMEOUT(60),
        WRITE_TIMEOUT(90)
    }
}
