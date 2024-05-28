package com.wx.chameleon.core.parser

import android.content.Context
import android.util.Log
import com.wx.chameleon.core.model.Element
import com.wx.chameleon.core.model.Elements
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.core.scanner.ChameleonPage
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParser.END_DOCUMENT
import org.xmlpull.v1.XmlPullParser.END_TAG
import org.xmlpull.v1.XmlPullParser.START_TAG
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.util.Stack

interface Parser {
    fun parse(page: ChameleonPage): List<Page>
}

class ParserImpl(
    context: Context,
    private val parserProvider: ParserProvider = XMLResourceParserProvider(context),
) : Parser {

    private val stack = Stack<Elements>()
    private val pages = mutableListOf<Page>()

    override fun parse(page: ChameleonPage): List<Page> =
        parserProvider.get(page)?.let { parseXml(it, page.packageName) } ?: emptyList()

    private fun parseXml(parser: XmlPullParser, packageName: String): List<Page> {
        var eventType = parser.eventType
        return try {
            while (eventType != END_DOCUMENT) {
                when (eventType) {
                    START_TAG -> handleStartTag(parser, packageName)
                    END_TAG -> handleEndTag(parser)
                }
                eventType = parser.next()
            }
            pages
        } catch (e: IOException) {
            Log.e(TAG, "parseXml IOException", e)
            pages
        } catch (e: XmlPullParserException) {
            Log.e(TAG, "parseXml XmlPullParserException", e)
            pages
        }
    }

    private fun handleStartTag(parser: XmlPullParser, packageName: String) {
        when (val e = ParserManager.parse(parser, packageName)) {
            is Elements -> stack.push(e)
            is Element -> stack.peek().children.add(e)
            else -> Log.d(TAG, "no op")
        }
    }

    private fun handleEndTag(parser: XmlPullParser) {
        val tags = ParserManager.elementsTags()
        if (parser.name in tags) {
            if (stack.size == 1) {
                (stack.pop() as? Page)?.let { pages.add(it) }
            } else if (stack.size > 1) {
                val element = stack.pop()
                stack.peek().children.add(element)
            }
        }
    }

    companion object {
        private const val TAG = "ParserImpl"
    }
}
