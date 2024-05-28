package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemDropdownBinding
import com.wx.chameleon.core.ui.page.BaseViewHolder
import com.wx.chameleon.core.ui.page.DropdownItemModel
import com.wx.chameleon.core.ui.page.newViewBinding

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
