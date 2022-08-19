package com.menilv.feature.home.adapter

import io.reactivex.subjects.PublishSubject


interface SearchAdapterView {
    val onItemSelected: PublishSubject<SearchItem>
}
