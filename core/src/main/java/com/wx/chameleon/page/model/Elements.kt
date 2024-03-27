package com.wx.chameleon.page.model

sealed class Elements(
    override val packageName: String,
    override val key: String,
    override val label: String,
) : Element() {
    abstract val containsSelf: Boolean
    abstract val children: MutableList<Element>
}
