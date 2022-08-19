package com.menilv.feature.home

import com.menilv.common.BaseView
import io.reactivex.Observable

interface HomeView : BaseView<HomeFullViewState> {
    fun onSearch(): Observable<String>
    fun onReset(): Observable<Unit>
}
