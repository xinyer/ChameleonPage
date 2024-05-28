/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.core.ui.page

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class PageAdapter(
    private val viewHolderFactory: ViewHolderFactory,
) : RecyclerView.Adapter<BaseViewHolder<ItemModel, ViewBinding>>() {

    private val items: MutableList<ItemModel> = mutableListOf()

    fun getItems() = items

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ItemModel, ViewBinding> {
        return viewHolderFactory.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemModel, ViewBinding>, position: Int) {
        holder.bind(items[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemModel, ViewBinding>,
        position: Int,
        payloads: List<Any>,
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }
//        holder.update(items[position], payloads)
    }

    override fun onViewRecycled(holder: BaseViewHolder<ItemModel, ViewBinding>) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun getItemViewType(position: Int): Int = items[position].type

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: List<ItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun update(item: ItemModel) {
        val index = this.items.indexOfFirst { it.id == item.id }
        if (index < 0 || index > this.items.size - 1) return
        this.items.removeAt(index)
        this.items.add(index, item)
//        notifyItemChanged(index, VALUE_CHANGED)
    }
}
