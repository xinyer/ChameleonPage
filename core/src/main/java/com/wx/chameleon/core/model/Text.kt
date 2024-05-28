package com.wx.chameleon.core.model

data class Text(
    override val packageName: String,
    override val key: String,
    override val label: String,
) : Element()
