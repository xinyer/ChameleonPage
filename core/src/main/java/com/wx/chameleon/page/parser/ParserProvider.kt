package com.wx.chameleon.page.parser

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import com.wx.chameleon.page.scanner.ChameleonPage
import org.xmlpull.v1.XmlPullParser

interface ParserProvider {
    fun get(page: ChameleonPage): XmlPullParser?
}

class XMLResourceParserProvider(private val context: Context) : ParserProvider {

    override fun get(page: ChameleonPage): XmlPullParser? {
        return try {
            context.createPackageContext(page.packageName, 0).resources.getXml(page.resId)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        } catch (e: Resources.NotFoundException) {
            null
        }
    }
}
