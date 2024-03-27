package com.wx.chameleon.page.model

sealed class Elements(override val label: String) : Element() {
    abstract val children: MutableList<Element>
}
