package com.wx.chameleon.core.ui.page

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.core.ui.page.holder.ButtonViewHolder
import com.wx.chameleon.core.ui.page.holder.DropdownViewHolder
import com.wx.chameleon.core.ui.page.holder.GroupEndViewHolder
import com.wx.chameleon.core.ui.page.holder.GroupStartViewHolder
import com.wx.chameleon.core.ui.page.holder.PageViewHolder
import com.wx.chameleon.core.ui.page.holder.TextViewHolder
import com.wx.chameleon.core.ui.page.holder.ToggleViewHolder

interface ViewHolderFactory {
    fun create(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemModel, ViewBinding>
}

class PageViewHolderFactory(private val navigateToPage: (Page) -> Unit) : ViewHolderFactory {

    @Suppress("UNCHECKED_CAST")
    override fun create(
        parent: ViewGroup,
        @ItemType viewType: Int,
    ): BaseViewHolder<ItemModel, ViewBinding> {
        val item = when (viewType) {
            ITEM_TYPE_PAGE -> PageViewHolder(parent, navigateToPage)
            ITEM_TYPE_TEXT -> TextViewHolder(parent)
            ITEM_TYPE_BUTTON -> ButtonViewHolder(parent)
            ITEM_TYPE_TOGGLE -> ToggleViewHolder(parent)
            ITEM_TYPE_DROPDOWN -> DropdownViewHolder(parent)
            ITEM_TYPE_GROUP_START -> GroupStartViewHolder(parent)
            ITEM_TYPE_GROUP_END -> GroupEndViewHolder(parent)
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
        return item as BaseViewHolder<ItemModel, ViewBinding>
    }
}
