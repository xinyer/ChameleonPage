package com.wx.chameleon.page.ui.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemToggleBinding
import com.wx.chameleon.page.ui.BaseViewHolder
import com.wx.chameleon.page.ui.ToggleItemModel
import com.wx.chameleon.page.ui.newViewBinding

class ToggleViewHolder(private val parent: ViewGroup) :
    BaseViewHolder<ToggleItemModel, ItemToggleBinding>(newViewBinding(parent)) {

    private var toggleItemModel: ToggleItemModel? = null

    override fun bind(item: ToggleItemModel) {
        toggleItemModel = item
        binding.apply {
            label.text = item.toggle.label
//            toggle.isChecked = item.toggle.isChecked == true
            toggle.setOnClickListener { _ ->
            }
        }
    }

    override fun unbind() {
        super.unbind()
    }
}
