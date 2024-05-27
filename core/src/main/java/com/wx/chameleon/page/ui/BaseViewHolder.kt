package com.wx.chameleon.page.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseViewHolder<T : ItemModel, VB : ViewBinding>(val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
    open fun unbind() {}
}

inline fun <reified T : ViewBinding> newViewBinding(parent: ViewGroup): T {
    val method = T::class.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java,
    )
    return method.invoke(null, LayoutInflater.from(parent.context), parent, false) as T
}
