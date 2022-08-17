package com.menilv.network.repository.http

import android.content.Context
import com.menilv.network.interceptor.Interceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import okhttp3.Cache
import okhttp3.OkHttpClient

class HttpClientImpl @Inject constructor(
    private val interceptor: Interceptor,
    @ApplicationContext val context: Context
) : HttpClient {

    override fun getClient(): OkHttpClient {
        val httpCacheDirectory = File(context.cacheDir, "http_cache")
        if (!httpCacheDirectory.exists())
            httpCacheDirectory.mkdir()

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .cache(Cache(httpCacheDirectory, 100 * 1024 * 1024)) // 100 MB

        interceptor.getApplicationInterceptors()
            .forEach {
                okHttpClientBuilder.addInterceptor(it)
            }
        return okHttpClientBuilder.build()
    }
}
