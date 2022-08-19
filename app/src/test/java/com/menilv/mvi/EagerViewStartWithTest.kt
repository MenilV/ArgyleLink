package com.menilv.mvi

import android.os.Parcelable
import com.menilv.common.BasePresenter
import com.menilv.common.BaseTest
import com.menilv.common.BaseView
import kotlinx.parcelize.Parcelize
import org.junit.Assert
import org.junit.Test
import java.util.*

class EagerViewStartWithTest : BaseTest() {

    @Parcelize
    class FullViewState constructor(val txt: String = "") : Parcelable

    private class EagerViewStartWith : BaseView<FullViewState> {
        internal var renderedStates: MutableList<String> = ArrayList()
        fun intent1(): io.reactivex.Observable<FullViewState> {
            return io.reactivex.Observable.just(FullViewState("Intent 1"))
                .startWith(io.reactivex.Observable.just(FullViewState("Before Intent 1")))
        }

        fun intent2(): io.reactivex.Observable<FullViewState> {
            return io.reactivex.Observable.just(FullViewState("Intent 2"))
        }

        override fun render(viewState: FullViewState) {
            renderedStates.add(viewState.txt)
        }
    }

    private class EagerPresenter : BasePresenter<EagerViewStartWith, FullViewState, FullViewState>() {
        override fun viewStateReducer(
            previousState: FullViewState,
            changes: FullViewState
        ): FullViewState = changes

        override fun getInitialState() = FullViewState()
        override fun bindIntents() {
            val intent1 = intent(EagerViewStartWith::intent1)
            val intent2 = intent(EagerViewStartWith::intent2)
            val res1 = intent1.flatMap { s -> io.reactivex.Observable.just(FullViewState("${s.txt} - Result 1")) }
            val res2 = intent2.flatMap { s -> io.reactivex.Observable.just(FullViewState("${s.txt} - Result 2")) }
            subscribeForViewStateChanges(res1, res2)
        }
    }

    @Test
    fun viewWithStartWithIntentWorksProperly() {
        val view = EagerViewStartWith()
        val presenter = EagerPresenter()
        presenter.attachView(view)
        Assert.assertEquals(
            Arrays.asList(
                "Before Intent 1 - Result 1",
                "Intent 1 - Result 1",
                "Intent 2 - Result 2"
            ),
            view.renderedStates
        )
    }
}