package com.wx.chameleon.core.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wx.chameleon.R
import com.wx.chameleon.databinding.FragmentCategoryBinding
import com.wx.chameleon.core.ui.pages.PagesFragment
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    private var binding: FragmentCategoryBinding? = null
    private val adapter = CategoryAdapter { navigateToPages(it) }
    private val viewModel by viewModels<CategoryViewModel> { CategoryViewModelFactory(this.requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false).apply {
            categoryList.layoutManager = LinearLayoutManager(context)
            categoryList.adapter = adapter
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.categories.collect {
                adapter.update(it)
            }
        }
    }

    private fun navigateToPages(category: String) {
        parentFragmentManager.commit {
            replace(R.id.container, PagesFragment.newInstance(category))
            setReorderingAllowed(true)
            addToBackStack(category)
        }
    }
}
