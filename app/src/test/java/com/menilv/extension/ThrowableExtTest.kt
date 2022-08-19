package com.menilv.extension

import com.menilv.exception.ApiErrorException
import com.menilv.exception.GenericDataException
import com.menilv.model.base.ErrorMessageEnum
import org.junit.Test

class ThrowableExtTest {

    @Test
    fun `Exception should return expecting error message enum`() {
        // Given
        val apiErrorException = ApiErrorException(
            ErrorMessageEnum.NO_INTERNET,
            Throwable("throwable")
        )
        val genericDataException = GenericDataException(
            ErrorMessageEnum.WRONG_RESPONSE,
            Throwable("throwable")
        )
        val otherException = Throwable("throwable")

        // When
        val apiErrorExceptionMessage = apiErrorException.toErrorMessage()
        val genericDateExceptionMessage = genericDataException.toErrorMessage()
        val otherExceptionMessage = otherException.toErrorMessage()

        // Then
        assert(apiErrorExceptionMessage == ErrorMessageEnum.NO_INTERNET)
        assert(genericDateExceptionMessage == ErrorMessageEnum.WRONG_RESPONSE)
        assert(otherExceptionMessage == ErrorMessageEnum.SOMETHING_WENT_WRONG)
    }
}
