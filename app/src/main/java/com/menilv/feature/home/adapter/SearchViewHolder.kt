package com.menilv.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding4.view.clicks
import com.menilv.common.BaseViewHolder
import com.menilv.databinding.VhSearchBinding

class SearchViewHolder(val parent: ViewGroup, searchAdapterView: SearchAdapterView) :
    BaseViewHolder<SearchItem, Any, VhSearchBinding>(
        VhSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
    init {
        itemView.clicks().map { data }.subscribe(searchAdapterView.onItemSelected)
    }
}
