package com.wx.chameleon.core.ui.page.item

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.IntDef

const val ITEM_TYPE_PAGE = 1
const val ITEM_TYPE_TEXT = 2
const val ITEM_TYPE_BUTTON = 3
const val ITEM_TYPE_TOGGLE = 4
const val ITEM_TYPE_DROPDOWN = 5
const val ITEM_TYPE_GROUP_START = 6
const val ITEM_TYPE_GROUP_END = 7

const val ACTION_CHAMELEON = "com.wx.action.CHAMELEON"
const val PERMISSION_CHAMELEON = "com.wx.chameleon.PERMISSION"
const val RECEIVER_CLASS_NAME = "com.xin.chameleon.sdk.ChameleonReceiver"
const val DATA_SCHEME = "wx"
const val DATA_HOST = "chameleon"
const val KEY_RESULT = "key_result"

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

    fun value(context: Context, callback: (String?) -> Unit) {}
    fun click(context: Context, vararg args: String?, callback: (String?) -> Unit) {}

    fun send(context: Context, path: String, callback: (String?) -> Unit) {
        val intent = Intent().apply {
            component = ComponentName(packageName, RECEIVER_CLASS_NAME)
            action = ACTION_CHAMELEON
            data = Uri.Builder().scheme(DATA_SCHEME)
                .authority(DATA_HOST)
                .path(path)
                .build()
        }
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val value = getResultExtras(true).getString(KEY_RESULT)
                callback.invoke(value)
            }
        }
        context.sendOrderedBroadcast(
            intent,
            PERMISSION_CHAMELEON,
            receiver,
            null,
            Activity.RESULT_OK,
            null,
            null,
        )
    }
}
