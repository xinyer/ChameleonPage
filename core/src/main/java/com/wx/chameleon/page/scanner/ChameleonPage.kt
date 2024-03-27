package com.wx.chameleon.page.scanner

import androidx.annotation.XmlRes

data class ChameleonPage(
    val packageName: String,
    @XmlRes val resId: Int,
)
