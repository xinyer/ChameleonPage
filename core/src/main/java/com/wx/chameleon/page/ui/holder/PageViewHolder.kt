package com.wx.chameleon.page.ui.holder

import android.view.ViewGroup
import com.wx.chameleon.databinding.ItemPageBinding
import com.wx.chameleon.page.model.Page
import com.wx.chameleon.page.ui.BaseViewHolder
import com.wx.chameleon.page.ui.PageItemModel
import com.wx.chameleon.page.ui.newViewBinding

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
