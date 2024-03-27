package com.wx.chameleon.page.model

data class Text(
    override val packageName: String,
    override val key: String,
    override val label: String,
) : Element()
