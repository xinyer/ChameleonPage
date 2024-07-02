package com.wx.chameleon.core.ui.page.item

import android.content.Context
import com.wx.chameleon.core.model.Toggle
import java.util.UUID

data class ToggleItemModel(
    val toggle: Toggle,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_TOGGLE,
) : ItemModel {

    override fun value(context: Context, callback: (String?) -> Unit) {
        if (toggle.key.isEmpty()) return
        send(context, toggle.key, callback)
    }

    override fun click(context: Context, vararg args: String?, callback: (String?) -> Unit) {
        if (toggle.click.isEmpty()) return
        val path = if (args.isEmpty()) toggle.click else toggle.click + "/" + args.reduce { acc, s -> "$acc/$s" }
        send(context, path, callback)
    }
}
