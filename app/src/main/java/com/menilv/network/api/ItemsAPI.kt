package com.menilv.network.api

import com.menilv.model.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsAPI {
    @GET("search/link-items")
    fun search(@Query("limit") limit: Int, @Query("q") query: String): Single<SearchResponse>
}
