package com.wx.chameleon.page.model

data class Group(
    override val label: String,
    override val containsSelf: Boolean = false,
    override val children: MutableList<Element>,
) : Elements("", "", label)
