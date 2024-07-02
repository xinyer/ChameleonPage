package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import com.wx.chameleon.core.ui.page.item.GroupEndItemModel
import com.wx.chameleon.core.ui.page.item.GroupStartItemModel
import com.wx.chameleon.databinding.ItemGroupEndBinding
import com.wx.chameleon.databinding.ItemGroupStartBinding

class GroupStartViewHolder(parent: ViewGroup) :
    BaseViewHolder<GroupStartItemModel, ItemGroupStartBinding>(newViewBinding(parent)) {

    override fun bind(item: GroupStartItemModel) {
        binding.label.text = item.title
    }
}

class GroupEndViewHolder(parent: ViewGroup) :
    BaseViewHolder<GroupEndItemModel, ItemGroupEndBinding>(newViewBinding(parent)) {

    override fun bind(item: GroupEndItemModel) {
        // ignore
    }
}
