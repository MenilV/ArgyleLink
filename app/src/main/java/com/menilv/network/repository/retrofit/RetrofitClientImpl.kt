package com.menilv.network.repository.retrofit

import com.menilv.di.ApiUrl
import com.menilv.network.api.ItemsAPI
import com.menilv.network.common.RxCallAdapterFactory
import com.menilv.network.repository.http.HttpClient
import com.menilv.network.serializer.Serializer
import javax.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClientImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val serializer: Serializer,
    @ApiUrl private val apiUrl: String,
) : RetrofitClient {
    override fun getItemsAPI(): ItemsAPI {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(MoshiConverterFactory.create(serializer.getMoshi()))
            .addCallAdapterFactory(RxCallAdapterFactory.create())
            .client(httpClient.getClient())
            .build()
            .create(ItemsAPI::class.java)
    }
}
