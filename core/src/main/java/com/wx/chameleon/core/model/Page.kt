package com.wx.chameleon.core.model

import com.wx.chameleon.core.ui.page.ButtonItemModel
import com.wx.chameleon.core.ui.page.DropdownItemModel
import com.wx.chameleon.core.ui.page.ItemModel
import com.wx.chameleon.core.ui.page.PageItemModel
import com.wx.chameleon.core.ui.page.TextItemModel
import com.wx.chameleon.core.ui.page.ToggleItemModel

data class Page(
    override val packageName: String,
    override val key: String,
    override val label: String,
    override val containsSelf: Boolean = true,
    override val children: MutableList<Element>,
    val title: String,
    val category: String,
) : Elements(packageName, key, label) {

    fun toItems(): List<ItemModel> = children.flatMap {
        if (it is Group) {
            it.toItems(packageName)
        } else {
            listOf(createItemModel(it))
        }
    }

    private fun createItemModel(element: Element): ItemModel = when (element) {
        is Text -> TextItemModel(element, packageName)
        is Button -> ButtonItemModel(element, packageName)
        is Toggle -> ToggleItemModel(element, packageName)
        is Dropdown -> DropdownItemModel(element, packageName)
        is Page -> PageItemModel(element)
        else -> throw IllegalArgumentException("Not support element type")
    }
}
