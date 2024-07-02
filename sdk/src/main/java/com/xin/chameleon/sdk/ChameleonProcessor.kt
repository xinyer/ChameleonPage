package com.xin.chameleon.sdk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.xin.chameleon.sdk.IntentKey.KEY_RESULT

abstract class ChameleonProcessor {

    abstract fun processAction(
        context: Context,
        receiver: BroadcastReceiver,
        intent: Intent,
        action: String?,
        vararg args: String?,
    )

    fun setResult(receiver: BroadcastReceiver, result: String) {
        val bundle = Bundle().apply {
            putString(KEY_RESULT, result)
        }
        receiver.setResultExtras(bundle)
    }
}
