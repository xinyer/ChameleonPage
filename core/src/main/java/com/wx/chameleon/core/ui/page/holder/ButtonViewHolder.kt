package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemButtonBinding
import com.wx.chameleon.core.ui.page.BaseViewHolder
import com.wx.chameleon.core.ui.page.ButtonItemModel
import com.wx.chameleon.core.ui.page.newViewBinding

class ButtonViewHolder(private val parent: ViewGroup) :
    BaseViewHolder<ButtonItemModel, ItemButtonBinding>(newViewBinding(parent)) {

    private var item: ButtonItemModel? = null

    override fun bind(item: ButtonItemModel) {
        this.item = item
        binding.apply {
            root.text = item.button.label
            root.setOnClickListener {
                item.click()
            }
        }
    }

    override fun unbind() {
        super.unbind()
        // TODO:
    }
}
