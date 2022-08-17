package com.menilv.feature.home

import android.os.Parcelable
import com.menilv.feature.home.adapter.SearchItem
import kotlinx.parcelize.Parcelize

sealed class HomeViewState

@Parcelize
data class HomeFullViewState(
    val error: Throwable? = null,
    val loading: Boolean? = null,
    val results: List<SearchItem>? = listOf()
) : Parcelable

class HomeSuccessViewState(val results: List<SearchItem>?) : HomeViewState()
class HomeLoadingViewState(val loading: Boolean?) : HomeViewState()
class HomeErrorViewState(val error: Throwable?) : HomeViewState()
