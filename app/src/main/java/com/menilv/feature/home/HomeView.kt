package com.menilv.feature.home

import androidx.lifecycle.Lifecycle
import com.menilv.common.BaseView
import io.reactivex.rxjava3.core.Observable

interface HomeView : BaseView<HomeFullViewState> {
    fun onLoad(): Observable<Lifecycle.Event>
}
