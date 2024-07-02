package com.wx.chameleon.core.scanner

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.GET_META_DATA
import android.content.pm.PackageManager.GET_RECEIVERS
import android.content.pm.PackageManager.ResolveInfoFlags
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.util.Log
import com.wx.chameleon.core.ui.page.item.DATA_HOST
import com.wx.chameleon.core.ui.page.item.DATA_SCHEME

interface Scanner {
    fun scan(): List<ChameleonPage>
}

class ScannerImpl(private val context: Context) : Scanner {

    override fun scan(): List<ChameleonPage> = queryReceivers().map { convert(it) }.also {
        Log.d(TAG, "scan: $it")
    }

    private fun convert(resolveInfo: ResolveInfo): ChameleonPage {
        val packageName = resolveInfo.activityInfo.packageName
        val className = resolveInfo.activityInfo.name
        val receiverInfo = context.packageManager.getReceiverInfo(
            ComponentName(packageName, className),
            GET_META_DATA,
        )
        val resId = receiverInfo.metaData.getInt(KEY_CHAMELEON_PAGE)
        return ChameleonPage(packageName, resId)
    }

    private fun queryReceivers(): List<ResolveInfo> {
        val intent = Intent(ACTION_CHAMELEON).apply {
            data = Uri.Builder().scheme(DATA_SCHEME).authority(DATA_HOST).build()
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.queryBroadcastReceivers(
                intent,
                ResolveInfoFlags.of(GET_RECEIVERS.toLong()),
            )
        } else {
            context.packageManager.queryBroadcastReceivers(intent, GET_RECEIVERS)
        }.also {
            Log.d(TAG, "queryReceivers: $it")
        }
    }

    companion object {
        private const val TAG = "Chameleon:Scanner"
        private const val ACTION_CHAMELEON = "com.wx.action.CHAMELEON"
        private const val KEY_CHAMELEON_PAGE = "chameleon_page"
    }
}