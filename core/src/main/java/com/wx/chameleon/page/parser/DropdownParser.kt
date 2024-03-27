package com.wx.chameleon.page.parser

import com.wx.chameleon.page.model.Dropdown
import org.xmlpull.v1.XmlPullParser

/**
 * Dropdown xml format
 *
 * <Dropdown
 *    click="action_test_dropdown"
 *    key="test_dropdown"
 *    label="Test Dropdown">
 *    <item>option 1</item>
 *    <item>option 2</item>
 *    <item>option 3</item>
 * </Dropdown>
 */

class DropdownParser : ElementParser {

    override fun parse(parser: XmlPullParser, packageName: String): Dropdown {
        val key = parser.getAttributeValue(null, ATTR_KEY)
        val label = parser.getAttributeValue(null, ATTR_LABEL)
        val click = parser.getAttributeValue(null, ATTR_CLICK)
        val options = parseOptions(parser)
        return Dropdown(
            packageName = packageName,
            key = key,
            label = label,
            click = click,
            options = options,
        )
    }

    private fun parseOptions(parser: XmlPullParser) : List<String> {
        val options = mutableListOf<String>()
        parser.next()
        while (parser.name != TAG) {
            if (parser.eventType == XmlPullParser.TEXT) {
                options.add(parser.text)
            }
            parser.next()
        }
        return options
    }

    companion object {
        const val TAG = "Dropdown"
    }
}
