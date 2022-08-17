package com.menilv.model.response

import android.os.Parcelable
import com.menilv.model.base.Entity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    @Json(name = "features")
    val features: Features? = Features(),
    @Json(name = "has_two_fa")
    val hasTwoFa: Boolean = false,
    @Json(name = "id")
    val id: String = "",
    @Json(name = "is_disabled")
    val isDisabled: Boolean = false,
    @Json(name = "item_id")
    val itemId: String = "",
    @Json(name = "kind")
    val kind: String = "",
    @Json(name = "known_limitations")
    val knownLimitations: String? = "",
    @Json(name = "logo_url")
    val logoUrl: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "status")
    val status: String = "",
    @Json(name = "status_details")
    val statusDetails: String? = "",
    @Json(name = "type")
    val type: String = ""
): Parcelable, Entity<String>{
    override fun id()= id
}
