package com.menilv.network.interceptor

import okhttp3.Interceptor

interface Interceptor {
    fun getApplicationInterceptors(): List<Interceptor>
    fun loggingInterceptor(): Interceptor
    fun headerInterceptor(): Interceptor
}
