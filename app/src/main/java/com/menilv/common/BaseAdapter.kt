package com.menilv.common

import androidx.recyclerview.widget.RecyclerView
import com.menilv.model.base.Entity

abstract class BaseAdapter<T : Entity<*>, VH : BaseViewHolder<T, *, *>> : RecyclerView.Adapter<VH>() {
    var items: List<T> by AdapterItemsDelegate()

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
