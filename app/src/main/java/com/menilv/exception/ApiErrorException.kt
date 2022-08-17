package com.menilv.exception

import com.menilv.model.base.ErrorMessageEnum

open class ApiErrorException(
    val errorMessage: ErrorMessageEnum,
    throwable: Throwable? = null
) : GenericException(throwable)
