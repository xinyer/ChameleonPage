package com.wx.chameleon.page.model

data class Button(
    override val packageName: String,
    override val key: String,
    override val label: String,
    val click: String,
) : Element()
