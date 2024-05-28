package com.wx.chameleon.core.parser

import com.wx.chameleon.core.model.Group
import org.xmlpull.v1.XmlPullParser

/**
 * Group xml format
 *
 * <Group
 *    key="test_group"
 *    label="Test Group"
 *    category="Test">
 *    ...
 * </Group>
 */
class GroupParser : ElementParser {

    override fun parse(parser: XmlPullParser, packageName: String): Group {
        val label = parser.getAttributeValue(null, ATTR_LABEL)
        return Group(
            label = label,
            children = mutableListOf(),
        )
    }

    companion object {
        const val TAG = "Group"
    }
}
