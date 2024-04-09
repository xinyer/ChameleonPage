package com.wx.chameleon.page.parser

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.util.Log
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
            Log.e(TAG, "get NameNotFoundException", e)
            null
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "get NotFoundException", e)
            null
        }
    }

    companion object {
        private const val TAG = "XMLResourceParserProvider"
    }
}
