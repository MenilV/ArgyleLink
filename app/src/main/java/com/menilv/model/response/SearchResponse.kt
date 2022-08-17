package com.menilv.model.response
import com.squareup.moshi.Json


data class SearchResponse(
    @Json(name = "results")
    val results: List<Result> = listOf()
)