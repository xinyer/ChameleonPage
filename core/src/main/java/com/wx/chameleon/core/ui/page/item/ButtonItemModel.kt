package com.wx.chameleon.core.ui.page.item

import android.content.Context
import com.wx.chameleon.core.model.Button
import java.util.UUID

data class ButtonItemModel(
    val button: Button,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_BUTTON,
) : ItemModel {

    override fun click(context: Context, vararg args: String?, callback: (String?) -> Unit) {
        if (button.click.isEmpty()) return
        send(context, button.click, callback)
    }
}
