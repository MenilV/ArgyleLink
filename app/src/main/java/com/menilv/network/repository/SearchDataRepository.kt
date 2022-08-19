package com.menilv.network.repository

import com.menilv.model.payload.SearchPayload
import com.menilv.model.response.SearchResponse
import com.menilv.network.common.DataRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchDataRepository @Inject constructor() :
    DataRepository<SearchPayload, SearchResponse>() {
    override fun fetchData(payload: SearchPayload): Observable<SearchResponse> {
        return retrofitClient
            .getItemsAPI()
            .search(payload.limit, payload.query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }
}
