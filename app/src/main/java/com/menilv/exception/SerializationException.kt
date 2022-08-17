package com.menilv.exception

import com.menilv.model.base.ErrorMessageEnum

class SerializationException(
    throwable: Throwable? = null
) : ApiErrorException(ErrorMessageEnum.WRONG_RESPONSE, throwable)
