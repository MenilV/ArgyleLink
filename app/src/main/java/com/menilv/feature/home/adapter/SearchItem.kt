package com.menilv.feature.home.adapter

import android.os.Parcelable
import com.menilv.model.base.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchItem(val data: String) : Entity<String>, Parcelable {
    override fun id(): String = data
}
