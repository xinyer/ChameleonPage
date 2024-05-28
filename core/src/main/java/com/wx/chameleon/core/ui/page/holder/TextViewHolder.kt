package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import com.wx.chameleon.core.ui.page.BaseViewHolder
import com.wx.chameleon.core.ui.page.TextItemModel
import com.wx.chameleon.core.ui.page.newViewBinding
import com.wx.chameleon.databinding.ItemTextBinding

class TextViewHolder(parent: ViewGroup) :
    BaseViewHolder<TextItemModel, ItemTextBinding>(newViewBinding(parent)) {

    private var item: TextItemModel? = null

    override fun bind(item: TextItemModel) {
        this.item = item
        binding.apply {
            label.text = item.text.label
//            value.text = item.text.value
        }
    }

    override fun unbind() {
        super.unbind()
    }
}
