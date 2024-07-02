package com.wx.chameleon.core.ui.page.item

import android.content.Context
import com.wx.chameleon.core.model.Text
import java.util.UUID

data class TextItemModel(
    val text: Text,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_TEXT,
) : ItemModel {

    override fun value(context: Context, callback: (String?) -> Unit) {
        if (text.key.isEmpty()) return
        send(context, text.key, callback)
    }
}
