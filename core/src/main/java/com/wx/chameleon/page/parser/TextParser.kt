package com.wx.chameleon.page.parser

import com.wx.chameleon.page.model.Text
import org.xmlpull.v1.XmlPullParser

/**
 * Text xml format
 *
 * <Text
 *    key="key_test_text"
 *    label="Test Text" />
 */
class TextParser : ElementParser {

    override fun parse(parser: XmlPullParser, packageName: String): Text {
        val key = parser.getAttributeValue(null, ATTR_KEY)
        val label = parser.getAttributeValue(null, ATTR_LABEL)
        return Text(
            packageName = packageName,
            key = key,
            label = label,
        )
    }

    companion object {
        const val TAG = "Text"
    }
}
