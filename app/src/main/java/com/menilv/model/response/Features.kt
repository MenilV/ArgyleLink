package com.menilv.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Features(
    @Json(name = "pay_distribution_card_update")
    val payDistributionCardUpdate: PayDistributionCardUpdate = PayDistributionCardUpdate(),
    @Json(name = "pay_distribution_update")
    val payDistributionUpdate: PayDistributionUpdate = PayDistributionUpdate()
): Parcelable