package com.wx.chameleon.core.model

data class Dropdown(
    override val packageName: String,
    override val key: String,
    override val label: String,
    val click: String,
    val options: List<String>,
) : Element()
