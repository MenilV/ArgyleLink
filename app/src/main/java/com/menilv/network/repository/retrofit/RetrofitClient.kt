package com.menilv.network.repository.retrofit

import com.menilv.network.api.ItemsAPI

interface RetrofitClient {
    fun getItemsAPI(): ItemsAPI
}
