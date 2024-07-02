package com.xin.chameleon.sdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

class ChameleonReceiver : BroadcastReceiver() {

    @SuppressWarnings("SpreadOperator")
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive: $intent")
        val segments = Uri.parse(intent.data?.path).pathSegments
        ProcessorFactory.get(context)?.processAction(
            context,
            this,
            intent,
            segments[0],
            *segments.subList(1, segments.size).toTypedArray(),
        )
    }

    companion object {
        private const val TAG = "ChameleonReceiver"
    }
}
