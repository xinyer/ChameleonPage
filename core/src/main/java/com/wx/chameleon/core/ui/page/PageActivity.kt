/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.core.ui.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wx.chameleon.R
import com.wx.chameleon.databinding.ActivityPageBinding

class PageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageBinding

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
        binding.toolbar.setNavigationOnClickListener { handleBackPress() }
        supportFragmentManager.addOnBackStackChangedListener {
            binding.toolbar.setNavigationIcon(
                if (supportFragmentManager.backStackEntryCount > 1) R.drawable.ic_left else R.drawable.ic_close,
            )
        }
    }

    private fun handleBackPress() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else if (supportFragmentManager.backStackEntryCount > 1) {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PageActivity::class.java))
        }
    }
}
