package com.menilv.extension

import com.menilv.R
import com.menilv.model.base.ErrorMessageEnum

fun ErrorMessageEnum.getErrorMessageStringRes(): Int {
    return when (this) {
        ErrorMessageEnum.SOMETHING_WENT_WRONG -> R.string.something_went_wrong
        ErrorMessageEnum.WRONG_RESPONSE -> R.string.wrong_response
        ErrorMessageEnum.SERVICE_TEMP_UNAVAILABLE -> R.string.service_is_temporarily_unavailable
        ErrorMessageEnum.NO_INTERNET -> R.string.no_internet
        else -> R.string.something_went_wrong
    }
}
