package com.wx.chameleon.page.parser

import com.wx.chameleon.page.model.Page
import org.xmlpull.v1.XmlPullParser

/**
 * Page xml format
 * <Page
 *    category="Test"
 *    key="key_test_page"
 *    label="Test Page">
 *         ...
 *  </Page>
 */

class PageParser : ElementParser {

    override fun parse(parser: XmlPullParser, packageName: String): Page {
        val key = parser.getAttributeValue(null, ATTR_KEY)
        val label = parser.getAttributeValue(null, ATTR_LABEL)
        val category = parser.getAttributeValue(null, ATTR_CATEGORY)
        return Page(
            packageName = packageName,
            key = key,
            label = label,
            category = category,
            children = mutableListOf(),
        )
    }

    companion object {
        const val TAG = "Page"
    }
}
