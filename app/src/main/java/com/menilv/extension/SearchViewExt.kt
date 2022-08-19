package com.menilv.extension

import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun SearchView.onTextChange(): Observable<String> {
    return Observable.create<String> { emitter ->
        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.count() >= 2)
                        emitter.onNext(it)
                }
                return true
            }
        }
        this.setOnQueryTextListener(listener)
    }
        .debounce(600, TimeUnit.MILLISECONDS)
        .doOnDispose {
            this.setOnQueryTextListener(null)
        }
}

fun SearchView.onReset(): Observable<Unit> {
    return Observable.create<Unit> { emitter ->
        val listener = SearchView.OnCloseListener {
            emitter.onNext(Unit)
            true
        }
        this.setOnCloseListener(listener)
    }
        .doOnDispose {
            this.setOnCloseListener(null)
        }
}
