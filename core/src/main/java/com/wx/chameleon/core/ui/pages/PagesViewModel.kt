package com.wx.chameleon.core.ui.pages

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.core.repository.PageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PagesViewModelFactory(
    private val context: Context,
    private val category: String,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pageRepository = PageRepository.instance(context)
        return modelClass.cast(PagesViewModel(category, pageRepository)) as T
    }
}

class PagesViewModel(
    category: String,
    pageRepository: PageRepository,
) : ViewModel() {

    private val _pages = MutableStateFlow<List<Page>>(emptyList())
    val pages: StateFlow<List<Page>> = _pages

    init {
        _pages.value = pageRepository.pagesBy(category)
    }
}
