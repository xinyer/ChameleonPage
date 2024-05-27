package com.wx.chameleon.page.repository

import com.wx.chameleon.page.model.Page
import com.wx.chameleon.page.parser.Parser
import com.wx.chameleon.page.scanner.Scanner

class PageRepository(private val scanner: Scanner, private val parser: Parser) {

    private val pages: MutableMap<String, List<Page>> = mutableMapOf()

    fun init() {
        pages.putAll(
            scanner.scan()
                .flatMap { parser.parse(it) }
                .filter { it.category.isNotBlank() && it.category.isNotEmpty() }
                .groupBy { it.category }
        )
    }

    fun allCategories(): List<String> = pages.keys.toList()

    fun pagesBy(category: String): List<Page> = pages[category] ?: emptyList()
}
