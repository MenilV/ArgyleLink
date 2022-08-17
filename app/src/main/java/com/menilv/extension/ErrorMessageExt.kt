package com.menilv.extension

import com.menilv.R
import com.menilv.model.base.ErrorMessageEnum

fun ErrorMessageEnum.getErrorMessageStringRes(): Int {
    return when (this) {
        ErrorMessageEnum.SOMETHING_WENT_WRONG -> R.string.something_went_wrong
        ErrorMessageEnum.WRONG_RESPONSE -> R.string.wrong_response
        else -> R.string.something_went_wrong
    }
}
