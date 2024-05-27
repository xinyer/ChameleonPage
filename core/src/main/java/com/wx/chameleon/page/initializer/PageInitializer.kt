package com.wx.chameleon.page.initializer

import android.content.Context
import androidx.startup.Initializer
import com.wx.chameleon.page.parser.ParserImpl
import com.wx.chameleon.page.repository.PageRepository
import com.wx.chameleon.page.scanner.ScannerImpl

class PageInitializer : Initializer<Unit>{
    override fun create(context: Context) {
        PageRepository(ScannerImpl(context), ParserImpl(context)).init()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
