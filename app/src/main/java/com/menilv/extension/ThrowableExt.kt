package com.menilv.extension

import com.menilv.exception.ApiErrorException
import com.menilv.exception.GenericDataException
import com.menilv.model.base.ErrorMessageEnum

fun Throwable.toErrorMessage(): ErrorMessageEnum {
    return when (this) {
        is ApiErrorException -> {
            this.errorMessage
        }
        is GenericDataException -> {
            this.error
        }
        else -> {
            ErrorMessageEnum.SOMETHING_WENT_WRONG
        }
    }
}
