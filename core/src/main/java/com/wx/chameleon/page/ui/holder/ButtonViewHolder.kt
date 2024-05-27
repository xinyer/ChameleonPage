package com.wx.chameleon.page.ui.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemButtonBinding
import com.wx.chameleon.page.ui.BaseViewHolder
import com.wx.chameleon.page.ui.ButtonItemModel
import com.wx.chameleon.page.ui.newViewBinding

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
