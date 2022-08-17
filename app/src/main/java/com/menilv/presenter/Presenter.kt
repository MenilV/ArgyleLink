package com.menilv.presenter

import android.os.Parcelable
import androidx.annotation.UiThread
import com.menilv.common.BaseView

interface Presenter<FVS : Parcelable> {
    var savedState: FVS?
    val latestViewState: FVS
    @UiThread
    fun attachView(view: BaseView<FVS>)
    @UiThread
    fun detachView()
    @UiThread
    fun destroy()
}
