package com.menilv.util.rx

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class RxLifecycle(lifecycleRegistry: LifecycleOwner) {
    private val subject = PublishSubject.create<Lifecycle.Event>().toSerialized()
    private val observer: RxLifecycleObserver = RxLifecycleObserver(subject)
    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(lifecycleRegistry)
    fun onEvent(): Observable<Lifecycle.Event> {
        return subject
    }

    fun onCreate(): Observable<Lifecycle.Event> {
        return onEvent().filter { other: Lifecycle.Event? -> Lifecycle.Event.ON_CREATE == other }
    }

    fun onStart(): Observable<Lifecycle.Event> {
        return onEvent().filter { other: Lifecycle.Event? -> Lifecycle.Event.ON_START == other }
    }

    fun onResume(): Observable<Lifecycle.Event> {
        return onEvent().filter { other: Lifecycle.Event? -> Lifecycle.Event.ON_RESUME == other }
    }

    fun onPause(): Observable<Lifecycle.Event> {
        return onEvent().filter { other: Lifecycle.Event? -> Lifecycle.Event.ON_PAUSE == other }
    }

    fun onStop(): Observable<Lifecycle.Event> {
        return onEvent().filter { other: Lifecycle.Event? -> Lifecycle.Event.ON_STOP == other }
    }

    fun onDestroy(): Observable<Lifecycle.Event> {
        return onEvent().filter { other: Lifecycle.Event? -> Lifecycle.Event.ON_DESTROY == other }
    }

    fun onAny(): Observable<Lifecycle.Event> {
        return onEvent().filter { other: Lifecycle.Event? -> Lifecycle.Event.ON_ANY == other }
    }

    fun <T> onlyIfResumedOrStarted(value: T): Observable<T> {
        return Observable.just("")
            .flatMap {
                val currentState = lifecycleRegistry.currentState
                if (currentState == Lifecycle.State.RESUMED || currentState == Lifecycle.State.STARTED) {
                    Observable.just(value)
                } else {
                    onResume()
                        .map { value }
                }
            }
    }

    companion object {
        fun with(lifecycle: ComponentActivity): RxLifecycle {
            return RxLifecycle(lifecycle)
        }

        fun with(lifecycle: Fragment): RxLifecycle {
            return RxLifecycle(lifecycle)
        }
    }

    init {
        lifecycleRegistry.lifecycle.addObserver(observer)
    }
}
