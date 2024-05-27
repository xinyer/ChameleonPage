package com.wx.chameleon.page.ui.category

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.wx.chameleon.page.parser.ParserImpl
import com.wx.chameleon.page.repository.PageRepository
import com.wx.chameleon.page.scanner.ScannerImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pageRepository = PageRepository(ScannerImpl(context), ParserImpl(context))
        return modelClass.cast(CategoryViewModel(pageRepository)) as T
    }
}

class CategoryViewModel(private val pageRepository: PageRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items

    init {
        viewModelScope.launch {
            _items.emit(pageRepository.allCategories())
        }
    }
}
