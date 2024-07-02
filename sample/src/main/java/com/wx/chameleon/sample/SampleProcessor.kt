package com.wx.chameleon.sample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.xin.chameleon.sdk.ChameleonProcessor

class SampleProcessor : ChameleonProcessor() {
    override fun processAction(
        context: Context,
        receiver: BroadcastReceiver,
        intent: Intent,
        action: String?,
        vararg args: String?,
    ) {
        when (action) {
            "test_text" -> setResult(receiver, "current time is ${System.currentTimeMillis()}")
            "click_button" -> setResult(receiver, "test button clicked")
            "test_toggle" -> setResult(receiver, Toggles.toggle.toString())
            "click_toggle" -> {
                val toggleState = args[0]?.toBoolean() ?: false
                Toggles.toggle = toggleState
                setResult(receiver, "test toggle changed to $toggleState")
            }
            "test_dropdown" -> setResult(receiver, Toggles.dropdown)
            "click_dropdown" -> {
                val option = args[0]
                Toggles.dropdown = option ?: "option 1"
                setResult(receiver, "test dropdown changed to $option")
            }
        }
    }
}

object Toggles {

    var toggle = false
    var dropdown = "option 1"
}
