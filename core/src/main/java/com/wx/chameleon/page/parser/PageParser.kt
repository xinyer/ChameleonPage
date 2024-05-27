package com.wx.chameleon.page.parser

import com.wx.chameleon.page.model.Page
import org.xmlpull.v1.XmlPullParser

/**
 * Page xml format
 * <Page
 *    title="title"
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
        val title = parser.getAttributeValue(null, ATTR_TITLE)
        return Page(
            key = key,
            label = label,
            category = category,
            title = title,
            packageName = packageName,
            children = mutableListOf(),
        )
    }

    companion object {
        const val TAG = "Page"
    }
}
