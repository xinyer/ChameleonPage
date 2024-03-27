package com.wx.chameleon.page.model

data class Toggle(
    override val packageName: String,
    override val key: String,
    override val label: String,
    val click: String,
) : Element()
