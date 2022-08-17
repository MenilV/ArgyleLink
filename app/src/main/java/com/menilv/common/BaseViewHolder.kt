package com.menilv.common

import androidx.annotation.CallSuper
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<D : Any, AV : Any, VDB : ViewDataBinding>(
    val binding: VDB,
    protected val adapterView: AV? = null
) : RecyclerView.ViewHolder(binding.root) {

    val bindingData = BindingData()
    lateinit var data: D

    @CallSuper
    open fun bind(data: D) {
        this.data = data
        binding.setVariable(BR.item, data)
        bindingData.data.set(data)
    }

    inner class BindingData {
        val data: ObservableField<D> = ObservableField()
    }
}
