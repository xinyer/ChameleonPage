package com.wx.chameleon.core.ui.page.item

import com.wx.chameleon.core.model.Page
import java.util.UUID

data class PageItemModel(
    val page: Page,
    override val packageName: String = "",
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_PAGE,
) : ItemModel
