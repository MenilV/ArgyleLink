package com.menilv.extension

import com.menilv.R
import com.menilv.model.base.ErrorMessageEnum
import org.junit.Test

class ErrorMessageExtTest {

    @Test
    fun testInitialState() {
        // Given

        // When
        val wrongResponseErrorMessage = ErrorMessageEnum.WRONG_RESPONSE.getErrorMessageStringRes()
        val somethingWentWrongErrorMessage = ErrorMessageEnum.SOMETHING_WENT_WRONG.getErrorMessageStringRes()
        val noInternetErrorMessage = ErrorMessageEnum.NO_INTERNET.getErrorMessageStringRes()
        val tempUnavailableErrorMessage = ErrorMessageEnum.SERVICE_TEMP_UNAVAILABLE.getErrorMessageStringRes()

        // Then
        assert(wrongResponseErrorMessage == R.string.wrong_response)
        assert(somethingWentWrongErrorMessage == R.string.something_went_wrong)
        assert(noInternetErrorMessage == R.string.no_internet)
        assert(tempUnavailableErrorMessage == R.string.service_is_temporarily_unavailable)
    }
}