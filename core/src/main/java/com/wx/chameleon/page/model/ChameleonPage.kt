package com.wx.chameleon.page.model

import androidx.annotation.XmlRes

data class ChameleonPage(
    val packageName: String,
    val className: String,
    @XmlRes val resId: Int,
)
