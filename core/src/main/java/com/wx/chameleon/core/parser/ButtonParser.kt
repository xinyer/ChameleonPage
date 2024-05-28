package com.wx.chameleon.core.parser

import com.wx.chameleon.core.model.Button
import org.xmlpull.v1.XmlPullParser

/**
 * Button xml format
 *
 * <Button
 *    click="click_button"
 *    key="key_test_button"
 *    label="Test Button" />
 */
class ButtonParser : ElementParser {

    override fun parse(parser: XmlPullParser, packageName: String): Button {
        val key = parser.getAttributeValue(null, ATTR_KEY)
        val label = parser.getAttributeValue(null, ATTR_LABEL)
        val click = parser.getAttributeValue(null, ATTR_CLICK)
        return Button(
            packageName = packageName,
            key = key,
            label = label,
            click = click,
        )
    }

    companion object {
        const val TAG = "Button"
    }
}
