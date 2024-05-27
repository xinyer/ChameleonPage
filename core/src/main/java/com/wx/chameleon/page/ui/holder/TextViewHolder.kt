package com.wx.chameleon.page.ui.holder

import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemTextBinding
import com.wx.chameleon.page.ui.BaseViewHolder
import com.wx.chameleon.page.ui.TextItemModel
import com.wx.chameleon.page.ui.newViewBinding

class TextViewHolder(parent: ViewGroup) :
    BaseViewHolder<TextItemModel, ItemTextBinding>(newViewBinding(parent)) {

    private var item: TextItemModel? = null
    private val mainHandler = Handler(Looper.getMainLooper())

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
