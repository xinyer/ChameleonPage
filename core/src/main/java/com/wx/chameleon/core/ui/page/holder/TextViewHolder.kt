package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import com.wx.chameleon.core.ui.page.item.TextItemModel
import com.wx.chameleon.databinding.ItemTextBinding

class TextViewHolder(private val parent: ViewGroup) :
    BaseViewHolder<TextItemModel, ItemTextBinding>(newViewBinding(parent)) {

    private var item: TextItemModel? = null

    override fun bind(item: TextItemModel) {
        this.item = item
        binding.apply {
            label.text = item.text.label
            item.value(parent.context) { value -> text.text = value }
        }
    }
}
