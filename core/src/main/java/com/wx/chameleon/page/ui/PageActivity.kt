/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.page.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.wx.chameleon.R
import com.wx.chameleon.databinding.ActivityPageBinding
import com.wx.chameleon.page.model.Page

class PageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageBinding
    private var pageId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPageBinding.inflate(layoutInflater).apply {
            setSupportActionBar(toolbar)
        }
        setContentView(binding.root)
        updatePage()
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        title?.let { binding.toolbar.setTitle(it.toString()) }
    }

    private fun updatePage() {
        intent.getStringExtra(KEY_ID)?.let { id ->
            if (id == pageId) return
            this.pageId = id
            DataStore.instance(this).getPage(id)?.let(::navigateToPage)
        }

        val closable = intent.getBooleanExtra(KEY_CLOSABLE, false)
//        binding.toolbar.setLeftIcon(if (closable) R.drawable.mx_benz_previous else 0)
        val backStackMaxCount = if (closable) 0 else 1
//        header.leftIcon.setOnClickListener { handleBackPress(closable) }
        supportFragmentManager.addOnBackStackChangedListener {
//            header.setLeftIcon(
//                if (supportFragmentManager.backStackEntryCount > backStackMaxCount) R.drawable.mx_benz_previous else 0,
//            )
        }
    }

    private fun navigateToPage(page: Page) {
        supportFragmentManager.commit {
            replace(R.id.container, PageFragment.newInstance(page))
            setReorderingAllowed(true)
            addToBackStack(page.packageName)
        }
    }

    private fun handleBackPress(closable: Boolean) {
        if (supportFragmentManager.backStackEntryCount == 1 && closable) {
            finish()
        } else if (supportFragmentManager.backStackEntryCount > 1) {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    companion object {
        private const val KEY_ID = "key_id"
        private const val KEY_CLOSABLE = "key_closable"

        fun start(context: Context, id: String, closable: Boolean) {
            context.startActivity(
                Intent(context, PageActivity::class.java).apply {
                    putExtra(KEY_ID, id)
                    putExtra(KEY_CLOSABLE, closable)
                },
            )
        }
    }
}
