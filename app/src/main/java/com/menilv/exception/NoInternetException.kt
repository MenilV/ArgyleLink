package com.menilv.exception

import com.menilv.model.base.ErrorMessageEnum

class NoInternetException(
    throwable: Throwable? = null
) : ApiErrorException(ErrorMessageEnum.NO_INTERNET, throwable)
