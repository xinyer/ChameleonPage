package com.wx.chameleon.page.model

data class Page(
    override val packageName: String,
    override val key: String,
    override val label: String,
    override val containsSelf: Boolean = true,
    override val children: MutableList<Element>,
    val category: String?
) : Elements(packageName, key, label)
