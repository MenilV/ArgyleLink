package com.menilv.util.rx

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.subjects.Subject

internal class RxLifecycleObserver(private val subject: Subject<Lifecycle.Event>) :
    DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        subject.onNext(Lifecycle.Event.ON_CREATE)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        subject.onNext(Lifecycle.Event.ON_START)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        subject.onNext(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        subject.onNext(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        subject.onNext(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        subject.onNext(Lifecycle.Event.ON_DESTROY)
    }
}
