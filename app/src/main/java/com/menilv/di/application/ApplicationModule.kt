package com.menilv.di.application

import com.menilv.BuildConfig
import com.menilv.di.ApiUrl
import com.menilv.network.interceptor.Interceptor
import com.menilv.network.interceptor.InterceptorImpl
import com.menilv.network.repository.http.HttpClient
import com.menilv.network.repository.http.HttpClientImpl
import com.menilv.network.repository.retrofit.RetrofitClient
import com.menilv.network.repository.retrofit.RetrofitClientImpl
import com.menilv.network.serializer.Serializer
import com.menilv.network.serializer.SerializerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
open class ApplicationModule {

     @Singleton
     @Provides
     fun httpClient(httpClient: HttpClientImpl): HttpClient = httpClient

     @Singleton
     @Provides
     fun interceptor(interceptor: InterceptorImpl): Interceptor = interceptor

     @Singleton
     @Provides
     fun retrofit(retrofit: RetrofitClientImpl): RetrofitClient = retrofit

     @Singleton
     @Provides
     fun serializer(serializer: SerializerImpl): Serializer = serializer

     @Singleton
     @Provides
     @ApiUrl
     fun apiUrl(): String = BuildConfig.API

}
