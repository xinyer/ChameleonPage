package com.wx.chameleon.core.parser

import com.wx.chameleon.core.model.Element
import org.xmlpull.v1.XmlPullParser

object ParserManager {

    private val parsers: Map<String, ElementParser> = mapOf(
        PageParser.TAG to PageParser(),
        GroupParser.TAG to GroupParser(),
        TextParser.TAG to TextParser(),
        ButtonParser.TAG to ButtonParser(),
        ToggleParser.TAG to ToggleParser(),
        DropdownParser.TAG to DropdownParser(),
    )

    fun parse(parser: XmlPullParser, packageName: String): Element? {
        return parsers[parser.name]?.parse(parser, packageName)
    }

    fun elementsTags(): Array<String> {
        return arrayOf(PageParser.TAG, GroupParser.TAG)
    }
}
