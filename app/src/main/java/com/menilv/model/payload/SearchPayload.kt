package com.menilv.model.payload

data class SearchPayload(val query: String, val limit: Int = 15)