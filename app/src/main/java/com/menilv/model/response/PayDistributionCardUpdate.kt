package com.menilv.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayDistributionCardUpdate(
    @Json(name = "supported")
    val supported: Boolean = false
): Parcelable