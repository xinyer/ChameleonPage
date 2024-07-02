package com.wx.chameleon.core.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.core.parser.Parser
import com.wx.chameleon.core.parser.ParserImpl
import com.wx.chameleon.core.scanner.Scanner
import com.wx.chameleon.core.scanner.ScannerImpl

class PageRepository private constructor(
    private val context: Context,
    private val scanner: Scanner = ScannerImpl(context),
    private val parser: Parser = ParserImpl(context),
) {

    private val pages: MutableMap<String, List<Page>> = mutableMapOf()

    fun init() {
        pages.putAll(
            scanner.scan()
                .flatMap { parser.parse(it) }
                .filter { it.category.isNotBlank() && it.category.isNotEmpty() }
                .groupBy { it.category },
        )
        Log.d(TAG, "${hashCode()} init: $pages")
    }

    fun allCategories(): List<String> = pages.keys.toList()

    fun pagesBy(category: String): List<Page> = pages[category] ?: emptyList()

    companion object {
        private const val TAG = "Chameleon.PageRepository"

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: PageRepository? = null

        fun instance(context: Context) = instance ?: synchronized(this) {
            instance ?: PageRepository(context).also { instance = it }
        }
    }
}
