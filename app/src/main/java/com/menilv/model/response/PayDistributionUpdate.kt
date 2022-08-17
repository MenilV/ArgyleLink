package com.menilv.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayDistributionUpdate(
    @Json(name = "action_types")
    val actionTypes: List<String> = listOf(),
    @Json(name = "amount_allocation")
    val amountAllocation: Boolean = false,
    @Json(name = "amount_precision")
    val amountPrecision: Double? = 0.0,
    @Json(name = "max_allocations")
    val maxAllocations: Int = 0,
    @Json(name = "percent_allocation")
    val percentAllocation: Boolean = false,
    @Json(name = "percent_precision")
    val percentPrecision: Double? = 0.0,
    @Json(name = "required_fields")
    val requiredFields: RequiredFields = RequiredFields(),
    @Json(name = "supported")
    val supported: Boolean = false
): Parcelable