package com.menilv.feature.home.adapter

import io.reactivex.rxjava3.subjects.PublishSubject

interface SearchAdapterView {
    val onItemSelected: PublishSubject<SearchItem>
}
