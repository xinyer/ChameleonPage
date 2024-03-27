package com.wx.chameleon.page.model

data class Page(
    override val key: String,
    override val label: String,
    override val children: MutableList<Element>,
    val category: String?
) : Elements(label)
