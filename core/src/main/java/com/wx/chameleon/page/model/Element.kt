package com.wx.chameleon.page.model

sealed class Element {
    abstract val packageName: String
    abstract val key: String
    abstract val label: String
}
