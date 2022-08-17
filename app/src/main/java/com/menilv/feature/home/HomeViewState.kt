package com.menilv.feature.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class HomeViewState

@Parcelize
data class HomeFullViewState(
    val error: Throwable? = null,
    val loading: Boolean? = null,
    val result: String? = null
) : Parcelable

class HomeSuccessViewState(val result: String?) : HomeViewState()
class HomeLoadingViewState(val loading: Boolean?) : HomeViewState()
class HomeErrorViewState(val error: Throwable?) : HomeViewState()
