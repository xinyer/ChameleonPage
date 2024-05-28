package com.wx.chameleon.core.ui.category

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wx.chameleon.core.repository.PageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pageRepository = PageRepository.instance(context)
        return modelClass.cast(CategoryViewModel(pageRepository)) as T
    }
}

class CategoryViewModel(pageRepository: PageRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories

    init {
        _categories.value = pageRepository.allCategories()
    }
}
