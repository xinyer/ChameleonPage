package com.wx.chameleon.page.ui.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemGroupEndBinding
import com.wx.chameleon.databinding.ItemGroupStartBinding
import com.wx.chameleon.page.ui.BaseViewHolder
import com.wx.chameleon.page.ui.GroupEndItemModel
import com.wx.chameleon.page.ui.GroupStartItemModel
import com.wx.chameleon.page.ui.newViewBinding

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
