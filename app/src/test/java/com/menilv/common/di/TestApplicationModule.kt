package com.menilv.common.di

import com.menilv.common.TestHttpClientImpl
import com.menilv.di.ApiUrl
import com.menilv.network.interceptor.Interceptor
import com.menilv.network.interceptor.InterceptorImpl
import com.menilv.network.repository.http.HttpClient
import com.menilv.network.repository.retrofit.RetrofitClient
import com.menilv.network.repository.retrofit.RetrofitClientImpl
import com.menilv.network.serializer.Serializer
import com.menilv.network.serializer.SerializerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TestApplicationModule {

    @Singleton
    @Provides
    fun serializer(serializer: SerializerImpl): Serializer = serializer

    @Singleton
    @Provides
    fun retrofit(retrofit: RetrofitClientImpl): RetrofitClient = retrofit

    @Singleton
    @Provides
    fun httpClient(httpClient: TestHttpClientImpl): HttpClient = httpClient

    @Singleton
    @Provides
    fun interceptor(interceptor: InterceptorImpl): Interceptor = interceptor

    @Provides
    @Singleton
    @ApiUrl
    fun apiUrl(): String = "http://localhost:8181"
}
