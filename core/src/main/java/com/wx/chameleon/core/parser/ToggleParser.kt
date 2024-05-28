package com.wx.chameleon.core.parser

import com.wx.chameleon.core.model.Toggle
import org.xmlpull.v1.XmlPullParser

/**
 * Toggle xml format
 *
 * <Toggle
 *    click="action_test_toggle"
 *    key="test_toggle"
 *    label="Test Toggle" />
 */
class ToggleParser : ElementParser {

    override fun parse(parser: XmlPullParser, packageName: String): Toggle {
        val key = parser.getAttributeValue(null, ATTR_KEY)
        val label = parser.getAttributeValue(null, ATTR_LABEL)
        val click = parser.getAttributeValue(null, ATTR_CLICK)
        return Toggle(
            packageName = packageName,
            key = key,
            label = label,
            click = click,
        )
    }

    companion object {
        const val TAG = "Toggle"
    }
}
