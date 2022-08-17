package com.menilv.model.response
import com.squareup.moshi.Json


data class SearchResponse(
    @Json(name = "results")
    val results: List<Result> = listOf()
)

data class Result(
    @Json(name = "features")
    val features: Features = Features(),
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
    val knownLimitations: Any? = Any(),
    @Json(name = "logo_url")
    val logoUrl: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "status")
    val status: String = "",
    @Json(name = "status_details")
    val statusDetails: Any? = Any(),
    @Json(name = "type")
    val type: String = ""
)

data class Features(
    @Json(name = "pay_distribution_card_update")
    val payDistributionCardUpdate: PayDistributionCardUpdate = PayDistributionCardUpdate(),
    @Json(name = "pay_distribution_update")
    val payDistributionUpdate: PayDistributionUpdate = PayDistributionUpdate()
)

data class PayDistributionCardUpdate(
    @Json(name = "supported")
    val supported: Boolean = false
)

data class PayDistributionUpdate(
    @Json(name = "action_types")
    val actionTypes: List<String> = listOf(),
    @Json(name = "amount_allocation")
    val amountAllocation: Boolean = false,
    @Json(name = "amount_precision")
    val amountPrecision: Any? = Any(),
    @Json(name = "max_allocations")
    val maxAllocations: Int = 0,
    @Json(name = "percent_allocation")
    val percentAllocation: Boolean = false,
    @Json(name = "percent_precision")
    val percentPrecision: Any? = Any(),
    @Json(name = "required_fields")
    val requiredFields: RequiredFields = RequiredFields(),
    @Json(name = "supported")
    val supported: Boolean = false
)

data class RequiredFields(
    @Json(name = "bank_account")
    val bankAccount: List<String> = listOf()
)