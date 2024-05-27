package com.wx.chameleon.page.ui.category

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mercedes.engineering.core.page.ui.PageViewModel
import com.wx.chameleon.page.repository.PageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoryViewModelFactory(context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.cast(CategoryViewModel(PageRepository())) as T
    }
}

class CategoryViewModel(private val pageRepository: PageRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items

    suspend fun load() {
        _items.emit(pageRepository.allCategories())
    }
}
