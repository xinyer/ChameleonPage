package com.wx.chameleon.page.model

data class Button(
    override val key: String,
    override val label: String,
    val click: String,
) : Element()
