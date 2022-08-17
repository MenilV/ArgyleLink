package com.menilv.common

interface BaseView<FVS> {
    fun render(viewState: FVS)
}
