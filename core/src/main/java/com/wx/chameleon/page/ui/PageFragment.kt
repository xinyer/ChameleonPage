/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.page.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils.loadAnimation
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.mercedes.engineering.core.CATEGORIES
import com.mercedes.engineering.core.R
import com.mercedes.engineering.core.databinding.FragmentPageBinding
import com.mercedes.engineering.core.model.DynamicPage
import com.mercedes.engineering.core.page.ui.PageViewModel
import com.mercedes.engineering.core.page.ui.PageViewModelFactory
import com.mercedes.engineering.core.page.ui.PagesAdapter
import com.mercedes.engineering.core.page.ui.TextItemModel
import com.mercedes.engineering.core.page.ui.holder.PageViewHolderFactory
import com.mercedes.engineering.sdk.log.EgLog

class PageFragment(private val page: DynamicPage? = null) : Fragment() {

    private var binding: FragmentPageBinding? = null

    private val viewHolderFactory by lazy { PageViewHolderFactory { page -> navigateToPage(page) } }
    private val viewModel by viewModels<PageViewModel> { PageViewModelFactory(page) }

    private var pagesAdapter: PagesAdapter = PagesAdapter(viewHolderFactory)
    private val updateValueHandler = Handler(Looper.getMainLooper())
    private val updateRunnable = object : Runnable {
        override fun run() {
            autoRefresh()
            updateValueHandler.postDelayed(this, UPDATE_INTERVAL)
        }
    }

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
        activity?.title = getTitle()
        (activity as? PageActivity)?.apply {
            refreshButton.isVisible = page?.refresh == true
            if (page?.refresh == true) {
                refreshButton.setOnClickListener { v ->
                    refresh()
                    loadAnimation(context, R.anim.rotate_clockwise).also {
                        v.startAnimation(it)
                    }
                }
            } else {
                refreshButton.setOnClickListener(null)
            }
        }
    }

    private fun getTitle(): String {
        return if (page?.title in CATEGORIES) {
            resources.getStringArray(R.array.category)[CATEGORIES.indexOf(page?.title)]
        } else {
            page?.title ?: ""
        }
    }

    private fun setupPagesList() {
        binding?.pagesView?.adapter = pagesAdapter
        viewModel.items.observe(viewLifecycleOwner) {
            pagesAdapter.update(it)
        }
        if (page?.hasAutoRefresh() == true) {
            updateValueHandler.post(updateRunnable)
        }
    }

    private fun autoRefresh() {
        val layoutManager = binding?.pagesView?.layoutManager as LinearLayoutManager
        val start = layoutManager.findFirstVisibleItemPosition()
        val end = layoutManager.findLastVisibleItemPosition()
        if (start != NO_POSITION && end != NO_POSITION) {
            pagesAdapter.getItems().subList(start, end + 1)
                .filterIsInstance<TextItemModel>()
                .filter { it.text.autoRefresh }
                .forEach { it.sendValueAction(false) }
        }
    }

    private fun navigateToPage(page: DynamicPage) {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out,
            )
            replace(
                R.id.container,
                newInstance(page),
            )
            setReorderingAllowed(true)
            addToBackStack(page.id)
        }
    }

    fun refresh() {
        pagesAdapter.getItems().forEach {
            it.reset()
            it.sendValueAction(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // fix memory leak start
        (activity as? PageActivity)?.header?.apply {
            if (page?.refresh == true) {
                rightIcon.setOnClickListener(null)
            }
        }
        updateValueHandler.removeCallbacks(updateRunnable)
        binding?.pagesView?.adapter = null
        binding = null
        // fix memory leak end
    }

    companion object {
        private const val UPDATE_INTERVAL = 3000L

        fun newInstance(page: DynamicPage): PageFragment {
            EgLog.ct(this, "newInstance page: ${page.id}")
            return PageFragment(page)
        }
    }
}
