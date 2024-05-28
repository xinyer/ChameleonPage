package com.wx.chameleon.core.ui.pages

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
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.core.ui.page.PageFragment
import com.wx.chameleon.databinding.FragmentPagesBinding
import kotlinx.coroutines.launch

class PagesFragment : Fragment() {

    private var binding: FragmentPagesBinding? = null
    private val adapter = PagesAdapter { navigateToPage(it) }
    private val viewModel by viewModels<PagesViewModel> {
        val category = arguments?.getString(KEY_CATEGORY)
        requireNotNull(category)
        PagesViewModelFactory(this.requireContext(), category)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPagesBinding.inflate(inflater, container, false).apply {
            pageList.layoutManager = LinearLayoutManager(context)
            pageList.adapter = adapter
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.pages.collect {
                adapter.update(it)
            }
        }
    }

    private fun navigateToPage(page: Page) {
        parentFragmentManager.commit {
            replace(R.id.container, PageFragment.newInstance(page))
            setReorderingAllowed(true)
            addToBackStack(page.id)
        }
    }

    companion object {
        private const val KEY_CATEGORY = "category"

        fun newInstance(category: String) = PagesFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_CATEGORY, category)
            }
        }
    }
}
