package com.menilv.network.common

import com.menilv.network.repository.retrofit.RetrofitClient
import io.reactivex.Observable
import javax.inject.Inject

abstract class DataRepository<P, R> {
    @Inject
    lateinit var retrofitClient: RetrofitClient

    protected abstract fun fetchData(payload: P): Observable<R>

    fun fetch(payload: P): Observable<R> {
        return fetchData(payload)
    }
}
