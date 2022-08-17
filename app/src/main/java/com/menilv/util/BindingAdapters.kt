package com.menilv.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.menilv.common.BaseAdapter
import com.menilv.model.base.Entity

@Suppress("UNCHECKED_CAST")
@BindingAdapter("android:data")
fun <T : Entity<*>> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>?) {
    if (recyclerView.adapter is BaseAdapter<*, *> && items != null) {
        (recyclerView.adapter as BaseAdapter<T, *>).items = items
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("android:data")
fun <T : Entity<*>> setViewPagerViewProperties(viewpager: ViewPager2, items: List<T>?) {
    if (viewpager.adapter is BaseAdapter<*, *> && items != null) {
        (viewpager.adapter as BaseAdapter<T, *>).items = items
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("android:currentItem")
fun <T : Entity<*>> setViewPagerCurrentItem(viewpager: ViewPager2, currentItem: Int) {
    viewpager.currentItem = currentItem
}

@BindingAdapter("android:srcUrl")
fun setImageFromUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView).load(url).into(imageView)
}