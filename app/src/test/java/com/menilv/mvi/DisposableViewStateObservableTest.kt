package com.menilv.mvi

import com.menilv.common.DisposableViewStateObserver
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.BehaviorSubject

import org.junit.Assert
import org.junit.Test

class DisposableViewStateObservableTest {

    @Test
    fun forwardOnNextAndNotCompleteToPublishShubject() {
        val subject = BehaviorSubject.create<String>()
        val sub = TestObserver<String>()
        subject.subscribeWith(sub)
        Observable.just("Hello")
            .subscribe(DisposableViewStateObserver<String>(subject))
        sub.assertNotComplete()
            .assertNoErrors()
            .assertValue("Hello")
    }

    @Test
    fun error() {
        val subject = BehaviorSubject.create<Any>()
        val sub = TestObserver<Any>()
        subject.subscribeWith(sub)
        val originalException = RuntimeException("I am the original Exception")
        val expectedException =
            IllegalStateException(
                "ViewState observable must not reach error state - onError()", originalException
            )
        try {
            Observable.error<Any>(originalException)
                .subscribe(DisposableViewStateObserver(subject))
            Assert.fail("Exception expected")
        } catch (e: Exception) {
            val cause = e.cause
            Assert.assertEquals(expectedException.message, cause?.message)
            Assert.assertEquals(originalException, cause?.cause)
        }
        sub.assertNotComplete()
            .assertNoValues()
    }
}