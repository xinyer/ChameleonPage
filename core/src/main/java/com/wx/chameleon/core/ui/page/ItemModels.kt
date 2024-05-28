/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.core.ui.page

import androidx.annotation.IntDef
import com.wx.chameleon.core.model.Button
import com.wx.chameleon.core.model.Dropdown
import com.wx.chameleon.core.model.Page
import com.wx.chameleon.core.model.Text
import com.wx.chameleon.core.model.Toggle
import java.util.UUID

const val ITEM_TYPE_PAGE = 1
const val ITEM_TYPE_TEXT = 2
const val ITEM_TYPE_BUTTON = 3
const val ITEM_TYPE_TOGGLE = 4
const val ITEM_TYPE_DROPDOWN = 5
const val ITEM_TYPE_GROUP_START = 6
const val ITEM_TYPE_GROUP_END = 7

@IntDef(
    value = [
        ITEM_TYPE_PAGE,
        ITEM_TYPE_TEXT,
        ITEM_TYPE_BUTTON,
        ITEM_TYPE_TOGGLE,
        ITEM_TYPE_DROPDOWN,
        ITEM_TYPE_GROUP_START,
        ITEM_TYPE_GROUP_END,
    ],
)
annotation class ItemType

interface ItemModel {
    val id: String
    @ItemType
    val type: Int
    val packageName: String

    fun action() {}
    fun click() {}
}

data class PageItemModel(
    val page: Page,
    override val packageName: String = "",
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_PAGE,
) : ItemModel

data class TextItemModel(
    val text: Text,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_TEXT,
) : ItemModel {

    override fun action() {
        if (text.key.isEmpty()) return
        // todo
    }
}

data class ButtonItemModel(
    val button: Button,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_BUTTON,
) : ItemModel {

    override fun click() {
        super.click()
        if (button.click.isEmpty()) return
        // TODO:
    }
}

data class ToggleItemModel(
    val toggle: Toggle,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_TOGGLE,
) : ItemModel {

    override fun action() {
        if (toggle.key.isEmpty()) return
        // TODO:
    }

    override fun click() {
        // TODO:
    }
}

data class DropdownItemModel(
    val dropdown: Dropdown,
    override val packageName: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_DROPDOWN,
) : ItemModel {

    override fun action() {
        if (dropdown.key.isEmpty()) return
    }

    override fun click() {
    }
}

interface GroupItemModel

data class GroupStartItemModel(
    val title: String,
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_GROUP_START,
    override val packageName: String = "",
) : ItemModel, GroupItemModel

data class GroupEndItemModel(
    override val id: String = UUID.randomUUID().toString(),
    override val type: Int = ITEM_TYPE_GROUP_END,
    override val packageName: String = "",
) : ItemModel, GroupItemModel
