package com.menilv.exception

import com.menilv.model.base.ErrorMessageEnum

open class GenericDataException(
    val error: ErrorMessageEnum,
    throwable: Throwable? = null
) : GenericException(throwable)
