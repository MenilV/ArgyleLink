package com.menilv.network.repository.http

import okhttp3.OkHttpClient

interface HttpClient {
    fun getClient(): OkHttpClient
}
