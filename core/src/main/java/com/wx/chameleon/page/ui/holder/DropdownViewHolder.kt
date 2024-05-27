package com.wx.chameleon.page.ui.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemDropdownBinding
import com.wx.chameleon.page.ui.BaseViewHolder
import com.wx.chameleon.page.ui.DropdownItemModel
import com.wx.chameleon.page.ui.newViewBinding

class DropdownViewHolder(parent: ViewGroup) :
    BaseViewHolder<DropdownItemModel, ItemDropdownBinding>(newViewBinding(parent)) {

    private var item: DropdownItemModel? = null

    override fun bind(item: DropdownItemModel) {
        this.item = item
        binding.apply {
            label.text = item.dropdown.label
        }
    }

    override fun unbind() {
        super.unbind()

    }
}
