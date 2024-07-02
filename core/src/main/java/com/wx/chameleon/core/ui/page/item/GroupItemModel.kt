/*
 * Copyright 2023 Mercedes-Benz Group China Ltd.
 * All Rights Reserved.
 * NOTICE: All information contained herein is, and remains the property of Mercedes-Benz Group.
 */

package com.wx.chameleon.core.ui.page.item

import java.util.UUID

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
