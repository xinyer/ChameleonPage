/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.page.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wx.chameleon.page.model.Page

class PageViewModelFactory(private val page: Page?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.cast(PageViewModel(page)) as T
    }
}

class PageViewModel(private val page: Page?) : ViewModel() {

    private val _items: MutableLiveData<List<ItemModel>> = MutableLiveData()

    val items: LiveData<List<ItemModel>> = _items

    fun load() {
//        _items.value = page?.toItems()
    }
}
