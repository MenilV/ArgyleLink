package com.menilv.extension

import com.menilv.feature.home.adapter.SearchItem
import com.menilv.model.response.SearchResponse

fun SearchResponse.toSearchItemList(): List<SearchItem> {
    return this.results.map { result ->
        SearchItem(result.name, result.kind, result.logoUrl)
    }
}
