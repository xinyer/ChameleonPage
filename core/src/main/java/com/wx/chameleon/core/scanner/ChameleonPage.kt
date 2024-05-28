package com.wx.chameleon.core.scanner

import androidx.annotation.XmlRes

data class ChameleonPage(
    val packageName: String,
    @XmlRes val resId: Int,
)
