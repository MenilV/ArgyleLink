package com.menilv.feature.home

import android.os.Parcelable
import com.menilv.feature.home.adapter.SearchItem
import com.menilv.model.response.Result
import kotlinx.parcelize.Parcelize

sealed class HomeViewState

@Parcelize
data class HomeFullViewState(
    val error: Throwable? = null,
    val loading: Boolean? = null,
    val result: List<SearchItem>? = null
) : Parcelable

class HomeSuccessViewState(val result: List<SearchItem>?) : HomeViewState()
class HomeLoadingViewState(val loading: Boolean?) : HomeViewState()
class HomeErrorViewState(val error: Throwable?) : HomeViewState()
