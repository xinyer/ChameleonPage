package com.wx.chameleon.core.initializer

import android.content.Context
import androidx.startup.Initializer
import com.wx.chameleon.core.repository.PageRepository

class PageInitializer : Initializer<Unit>{
    override fun create(context: Context) {
        PageRepository.instance(context).init()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}
