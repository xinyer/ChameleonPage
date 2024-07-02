package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import android.widget.Toast
import com.wx.chameleon.core.ui.page.item.ToggleItemModel
import com.wx.chameleon.databinding.ItemToggleBinding

class ToggleViewHolder(private val parent: ViewGroup) :
    BaseViewHolder<ToggleItemModel, ItemToggleBinding>(newViewBinding(parent)) {

    override fun bind(item: ToggleItemModel) {
        binding.apply {
            label.text = item.toggle.label
            item.value(parent.context) { value -> toggle.isChecked = value.toBoolean() }
            toggle.setOnClickListener { v ->
                item.click(v.context, toggle.isChecked.toString()) { value ->
                    value?.let { Toast.makeText(v.context, it, Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }
}
