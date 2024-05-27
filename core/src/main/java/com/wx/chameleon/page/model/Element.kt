package com.wx.chameleon.page.model

import java.util.UUID

sealed class Element {
    val id: String = UUID.randomUUID().toString()
    abstract val packageName: String
    abstract val key: String
    abstract val label: String
}
