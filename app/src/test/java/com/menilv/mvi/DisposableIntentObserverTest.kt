package com.menilv.mvi

import com.menilv.common.BaseTest
import com.menilv.common.DisposableIntentObserver
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.junit.Assert
import org.junit.Test

class DisposableIntentObserverTest : BaseTest() {

    @Test
    fun forwardOnNextAndOnCompleteToPublishShubject() {
        val subject = PublishSubject.create<String>()
        val sub = TestObserver<String>()
        subject.subscribeWith(sub)
        Observable.just("Hello")
            .subscribe(DisposableIntentObserver<String>(subject))
        sub.assertNoErrors()
            .assertComplete()
            .assertResult("Hello")
    }

    @Test
    fun error() {
        val subject = PublishSubject.create<Any>()
        val sub = TestObserver<Any>()
        subject.subscribeWith(sub)
        val originalException = RuntimeException("I am the original Exception")
        val expectedException =
            IllegalStateException("View intents must not throw errors", originalException)
        try {
            Observable.error<Any>(originalException)
                .subscribe(DisposableIntentObserver(subject))
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