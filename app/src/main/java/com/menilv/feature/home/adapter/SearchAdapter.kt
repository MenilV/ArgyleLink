package com.menilv.feature.home.adapter

import android.view.ViewGroup
import com.menilv.common.BaseAdapter
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class SearchAdapter @Inject constructor() : BaseAdapter<SearchItem, SearchViewHolder>(),
    SearchAdapterView {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(parent, this)

    override val onItemSelected: PublishSubject<SearchItem> = PublishSubject.create()
}
