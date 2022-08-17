package com.menilv.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequiredFields(
    @Json(name = "bank_account")
    val bankAccount: List<String> = listOf()
): Parcelable