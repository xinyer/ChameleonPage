/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.core.ui.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.wx.chameleon.R
import com.wx.chameleon.databinding.FragmentPageBinding
import com.wx.chameleon.core.model.Page

class PageFragment(private val page: Page) : Fragment() {

    private var binding: FragmentPageBinding? = null

    private val viewHolderFactory by lazy { PageViewHolderFactory { page -> navigateToPage(page) } }
    private val viewModel by viewModels<PageViewModel> { PageViewModelFactory(page) }

    private var pageAdapter: PageAdapter = PageAdapter(viewHolderFactory)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeader()
        setupPagesList()
        viewModel.load()
    }

    private fun setupHeader() {
        activity?.title = page.title
    }

    private fun setupPagesList() {
        binding?.pagesView?.adapter = pageAdapter
        viewModel.items.observe(viewLifecycleOwner) {
            pageAdapter.update(it)
        }
    }

    private fun navigateToPage(page: Page) {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out,
            )
            replace(R.id.container, newInstance(page))
            setReorderingAllowed(true)
            addToBackStack(page.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.pagesView?.adapter = null
        binding = null
    }

    companion object {

        fun newInstance(page: Page): PageFragment {
            return PageFragment(page)
        }
    }
}
