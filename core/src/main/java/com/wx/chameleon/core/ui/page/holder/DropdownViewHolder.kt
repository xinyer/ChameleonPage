package com.wx.chameleon.core.ui.page.holder

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.wx.chameleon.core.ui.page.item.DropdownItemModel
import com.wx.chameleon.databinding.ItemDropdownBinding

class DropdownViewHolder(private val parent: ViewGroup) :
    BaseViewHolder<DropdownItemModel, ItemDropdownBinding>(newViewBinding(parent)) {

    override fun bind(item: DropdownItemModel) {
        var current: String? = null
        binding.apply {
            label.text = item.dropdown.label
            item.value(parent.context) { value ->
                current = value
                spinner.setSelection(item.dropdown.options.indexOf(value))
            }
            ArrayAdapter(parent.context, android.R.layout.simple_spinner_item, item.dropdown.options).also { adapter ->
                spinner.adapter = adapter
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        item.click(parent.context, adapter.getItem(position), position.toString()) { value ->
                            if (value != null && adapter.getItem(position) != current) {
                                Toast.makeText(parent.context, value, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // no op
                    }
                }
            }
        }
    }
}
