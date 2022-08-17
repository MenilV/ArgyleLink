package com.menilv.network.common

abstract class BaseInterceptor {
    abstract var token: String
    fun requestInterceptor(): okhttp3.Interceptor {
        return okhttp3.Interceptor { chain ->
            val builder = chain.request().newBuilder()
            if (token.isNotEmpty())
                builder.addHeader("Authorization", token)
            // Intercepting and adjusting request

            chain.proceed(builder.build())
        }
    }

    fun responseInterceptor(): okhttp3.Interceptor {
        return okhttp3.Interceptor { chain ->
            val response = chain.proceed(chain.request())
            // Intercepting and adjusting response
            response
        }
    }
}
