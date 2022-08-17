package com.menilv.network.serializer

import com.squareup.moshi.Moshi

interface Serializer {
    fun getMoshi(): Moshi
}
