package com.xin.chameleon.sdk

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import org.xmlpull.v1.XmlPullParser

object ProcessorFactory {

    private var processor: ChameleonProcessor? = null

    fun get(context: Context): ChameleonProcessor? {
        if (processor == null) {
            processor = ProcessorCreator(context).get()
        }
        return processor
    }
}

/**
 * <Chameleon processor="com.wx.chameleon.sample.SampleProcessor">
 *     ...
 * </Chameleon>
 */
class ProcessorCreator(private val context: Context) {

    companion object {
        private const val TAG = "ProcessorCreator"
        private const val TAG_CHAMELEON = "Chameleon"
        private const val TAG_PROCESSOR = "processor"
        private const val KEY_CONFIG = "chameleon_page"
    }

    @SuppressWarnings("TooGenericExceptionCaught")
    fun get(): ChameleonProcessor? {
        val className = getProcessorClassName()
        Log.i(TAG, "get processor class name: $className")
        if (className.isNullOrEmpty()) {
            Log.e(TAG, "can not get processor class name")
            return null
        }
        return try {
            val classType = Class.forName(className)
            classType.getDeclaredConstructor().newInstance() as? ChameleonProcessor
        } catch (e: Exception) {
            Log.e(TAG, "can not create processor.", e)
            null
        }
    }

    private fun getProcessorClassName(): String? {
        val resId = getXmlConfigId()
        val parser = context.resources.getXml(resId)
        var eventType = parser.eventType
        var className: String? = null
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && parser.name == TAG_CHAMELEON) {
                className = parser.getAttributeValue(null, TAG_PROCESSOR)
                break
            }
            eventType = parser.next()
        }
        return className
    }

    private fun getXmlConfigId(): Int {
        val providerInfo = context.packageManager.getReceiverInfo(
            ComponentName(
                context,
                ChameleonReceiver::class.java,
            ),
            PackageManager.GET_META_DATA,
        )
        return providerInfo.metaData.getInt(KEY_CONFIG)
    }
}
