package com.wx.chameleon.core.ui.page.item

import android.content.Context
import com.wx.chameleon.core.model.Dropdown
import java.util.UUID

data class DropdownItemModel(
    val dropdown: Dropdown,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_DROPDOWN,
) : ItemModel {

    override fun value(context: Context, callback: (String?) -> Unit) {
        if (dropdown.key.isEmpty()) return
        send(context, dropdown.key, callback)
    }

    override fun click(context: Context, vararg args: String?, callback: (String?) -> Unit) {
        if (dropdown.click.isEmpty()) return
        val path = if (args.isEmpty()) dropdown.click else dropdown.click + "/" + args.reduce { acc, s -> "$acc/$s" }
        send(context, path, callback)
    }
}
