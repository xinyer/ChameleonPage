package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import android.widget.Toast
import com.wx.chameleon.core.ui.page.item.ButtonItemModel
import com.wx.chameleon.databinding.ItemButtonBinding

class ButtonViewHolder(private val parent: ViewGroup) :
    BaseViewHolder<ButtonItemModel, ItemButtonBinding>(newViewBinding(parent)) {

    override fun bind(item: ButtonItemModel) {
        binding.apply {
            text.text = item.button.label
            text.setOnClickListener {
                item.click(parent.context) { value ->
                    Toast.makeText(parent.context, value, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
