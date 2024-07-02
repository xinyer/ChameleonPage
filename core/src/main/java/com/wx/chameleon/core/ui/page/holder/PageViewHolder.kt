package com.wx.chameleon.core.ui.page.holder

import android.view.ViewGroup
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.core.ui.page.item.PageItemModel
import com.wx.chameleon.databinding.ItemPageBinding

class PageViewHolder(
    parent: ViewGroup,
    private val navigateToPage: (Page) -> Unit,
) : BaseViewHolder<PageItemModel, ItemPageBinding>(newViewBinding(parent)) {

    override fun bind(item: PageItemModel) {
        binding.apply {
            title.text = item.page.label
            root.setOnClickListener { navigateToPage.invoke(item.page) }
        }
    }
}
