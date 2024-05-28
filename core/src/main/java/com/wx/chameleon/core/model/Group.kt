package com.wx.chameleon.core.model

import com.wx.chameleon.core.ui.page.ButtonItemModel
import com.wx.chameleon.core.ui.page.DropdownItemModel
import com.wx.chameleon.core.ui.page.GroupEndItemModel
import com.wx.chameleon.core.ui.page.GroupStartItemModel
import com.wx.chameleon.core.ui.page.ItemModel
import com.wx.chameleon.core.ui.page.PageItemModel
import com.wx.chameleon.core.ui.page.TextItemModel
import com.wx.chameleon.core.ui.page.ToggleItemModel

data class Group(
    override val label: String,
    override val containsSelf: Boolean = false,
    override val children: MutableList<Element>,
) : Elements("", "", label) {

    fun toItems(packageName: String): List<ItemModel> = mutableListOf<ItemModel>().apply {
        add(GroupStartItemModel(label))
        addAll(children.map { createItemModel(it, packageName) })
        add(GroupEndItemModel())
    }

    private fun createItemModel(element: Element, packageName: String) =
        when (element) {
            is Text -> TextItemModel(element, packageName)
            is Button -> ButtonItemModel(element, packageName)
            is Toggle -> ToggleItemModel(element, packageName)
            is Dropdown -> DropdownItemModel(element, packageName)
            is Page -> PageItemModel(element)
            else -> throw IllegalArgumentException("unsupported element type.")
        }
}
