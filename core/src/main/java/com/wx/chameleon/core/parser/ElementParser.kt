package com.wx.chameleon.core.parser

import com.wx.chameleon.core.model.Element
import org.xmlpull.v1.XmlPullParser

const val ATTR_KEY = "key"
const val ATTR_LABEL = "label"
const val ATTR_CATEGORY = "category"
const val ATTR_TITLE = "title"
const val ATTR_CLICK = "click"

interface ElementParser {
    fun parse(parser: XmlPullParser, packageName: String): Element?
}
