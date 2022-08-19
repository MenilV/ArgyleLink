package com.menilv.mvi

import android.os.Parcelable
import com.menilv.common.BasePresenter
import com.menilv.common.BaseTest
import com.menilv.common.BaseView
import io.reactivex.subjects.PublishSubject
import java.util.Arrays
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.parcelize.Parcelize
import org.junit.Assert
import org.junit.Test

class BasePresenterTest : BaseTest() {
    @Parcelize
    class FullViewState constructor(val txt: String = "") : Parcelable

    @Test
    fun bindIntentsAndUnbindIntentsOnlyOnce() {
        val bindInvocations = AtomicInteger(0)
        val unbindInvocations = AtomicInteger(0)
        val view = object : BaseView<FullViewState> {
            override fun render(viewState: FullViewState) = Unit
        }
        val presenter = object : BasePresenter<BaseView<FullViewState>, FullViewState, FullViewState>() {
            override fun bindIntents() {
                bindInvocations.incrementAndGet()
            }

            override fun unbindIntents() {
                super.unbindIntents()
                unbindInvocations.incrementAndGet()
            }

            override fun viewStateReducer(
                previousState: FullViewState,
                changes: FullViewState
            ): FullViewState {
                return changes
            }

            override fun getInitialState(): FullViewState = FullViewState()
        }
        presenter.attachView(view)
        presenter.detachView()
        presenter.attachView(view)
        presenter.detachView()
        presenter.attachView(view)
        presenter.detachView()
        presenter.destroy()
        Assert.assertEquals(1, bindInvocations.get().toLong())
        Assert.assertEquals(1, unbindInvocations.get().toLong())
    }

    @Test
    fun keepUnderlayingSubscriptions() {
        val intentsData = ArrayList<String>()
        val businessLogic = PublishSubject.create<String>()
        val view = KeepUndelyingSubscriptionsView()
        val presenter = object : BasePresenter<KeepUndelyingSubscriptionsView, String, FullViewState>() {
            override fun viewStateReducer(
                previousState: FullViewState,
                changes: String
            ): FullViewState {
                return FullViewState(changes)
            }

            override fun getInitialState() = FullViewState()
            override fun bindIntents() {
                intent(KeepUndelyingSubscriptionsView::anIntent).subscribe { s -> intentsData.add(s) }
                subscribeForViewStateChanges(businessLogic)
            }
        }
        view.anIntent.onNext("Should never hit the presenter because View not attached")
        Assert.assertTrue(intentsData.isEmpty())
        presenter.attachView(view)
        view.anIntent.onNext("1 Intent")
        Assert.assertEquals(Arrays.asList("1 Intent"), intentsData)
        businessLogic.onNext("1 bl")
        Assert.assertEquals(Arrays.asList("1 bl"), view.renderedModels)
        businessLogic.onNext("2 bl")
        Assert.assertEquals(Arrays.asList("1 bl", "2 bl"), view.renderedModels)
        view.anIntent.onNext("2 Intent")
        Assert.assertEquals(Arrays.asList("1 Intent", "2 Intent"), intentsData)
        // Detach View temporarily
        presenter.detachView()
        Assert.assertFalse(view.anIntent.hasObservers())
        businessLogic.onNext("3 bl")
        Assert.assertEquals(Arrays.asList("1 bl", "2 bl"), view.renderedModels)
        businessLogic.onNext("4 bl")
        Assert.assertEquals(Arrays.asList("1 bl", "2 bl"), view.renderedModels)
        view.anIntent.onNext("Doesn't hit presenter because view not attached to presenter")
        Assert.assertEquals(Arrays.asList("1 Intent", "2 Intent"), intentsData)
        // Reattach View
        presenter.attachView(view) // This will call view.render() method
        Assert.assertEquals(Arrays.asList("1 bl", "2 bl", "4 bl"), view.renderedModels)
        view.anIntent.onNext("3 Intent")
        Assert.assertEquals(Arrays.asList("1 Intent", "2 Intent", "3 Intent"), intentsData)
        businessLogic.onNext("5 bl")
        Assert.assertEquals(Arrays.asList("1 bl", "2 bl", "4 bl", "5 bl"), view.renderedModels)
        // Detach View permanently
        presenter.detachView()
        presenter.destroy()
        Assert.assertFalse(businessLogic.hasObservers()) // No more observers
        Assert.assertFalse(view.anIntent.hasObservers()) // No more observers
        view.anIntent.onNext("This will never be delivered to presenter")
        Assert.assertEquals(Arrays.asList("1 Intent", "2 Intent", "3 Intent"), intentsData)
        businessLogic.onNext("This will never reach the view")
        Assert.assertEquals(Arrays.asList("1 bl", "2 bl", "4 bl", "5 bl"), view.renderedModels)
    }

    @Test
    fun resetOnViewDetachedPermanently() {
        val bindInvocations = AtomicInteger(0)
        val unbindInvocations = AtomicInteger(0)
        val view = object : BaseView<FullViewState> {
            override fun render(viewState: FullViewState) = Unit
        }
        val presenter = object : BasePresenter<BaseView<FullViewState>, FullViewState, FullViewState>() {
            override fun viewStateReducer(
                previousState: FullViewState,
                changes: FullViewState
            ): FullViewState = changes

            override fun getInitialState(): FullViewState = FullViewState()
            override fun bindIntents() {
                bindInvocations.incrementAndGet()
            }

            override fun unbindIntents() {
                super.unbindIntents()
                unbindInvocations.incrementAndGet()
            }
        }
        presenter.attachView(view)
        presenter.detachView()
        presenter.destroy()
        presenter.attachView(view)
        presenter.detachView()
        presenter.attachView(view)
        presenter.detachView()
        presenter.destroy()
        Assert.assertEquals(2, bindInvocations.get().toLong())
        Assert.assertEquals(2, unbindInvocations.get().toLong())
    }

    private class KeepUndelyingSubscriptionsView : BaseView<FullViewState> {
        internal var renderedModels: MutableList<String> = ArrayList()
        internal var anIntent = PublishSubject.create<String>()
        override fun render(model: FullViewState) {
            renderedModels.add(model.txt)
        }
    }
}
