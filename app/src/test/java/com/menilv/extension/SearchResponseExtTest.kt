package com.menilv.extension

import com.menilv.common.BaseTest
import com.menilv.feature.home.adapter.SearchItem
import com.menilv.model.response.SearchResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test

class SearchResponseExtTest : BaseTest() {

    @Test
    fun `Given search response should be mapped to proper search items list`() {
        // Given
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(SearchResponse::class.java)
        val response = adapter.fromJson(getJson("/searchItems.json"))

        // When
        val result = response?.toSearchItemList()

        // Then
        result?.let {
            assert(it.isNotEmpty())
            assert(it.size == response.results.size)
            assert(it[0].javaClass == SearchItem::class.java)
        }

    }
}