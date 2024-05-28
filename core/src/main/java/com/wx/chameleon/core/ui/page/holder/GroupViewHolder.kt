package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemGroupEndBinding
import com.wx.chameleon.databinding.ItemGroupStartBinding
import com.wx.chameleon.core.ui.page.BaseViewHolder
import com.wx.chameleon.core.ui.page.GroupEndItemModel
import com.wx.chameleon.core.ui.page.GroupStartItemModel
import com.wx.chameleon.core.ui.page.newViewBinding

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
