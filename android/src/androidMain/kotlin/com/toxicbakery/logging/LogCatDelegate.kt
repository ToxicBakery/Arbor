package com.toxicbakery.logging

import android.util.Log

/**
 * Default Android delegate for logging.
 */
object LogCatDelegate : LogDelegate {

    override fun writeLog(level: Int, tag: String, msg: String) {
        when (level) {
            Arbor.DEBUG -> Log.d(tag, msg)
            Arbor.ERROR -> Log.e(tag, msg)
            Arbor.INFO -> Log.i(tag, msg)
            Arbor.VERBOSE -> Log.v(tag, msg)
            Arbor.WARNING -> Log.w(tag, msg)
            Arbor.WTF -> Log.wtf(tag, msg)
        }
    }

    override fun writeLog(level: Int, tag: String, msg: String, throwable: Throwable) {
        when (level) {
            Arbor.DEBUG -> Log.d(tag, msg, throwable)
            Arbor.ERROR -> Log.e(tag, msg, throwable)
            Arbor.INFO -> Log.i(tag, msg, throwable)
            Arbor.VERBOSE -> Log.v(tag, msg, throwable)
            Arbor.WARNING -> Log.w(tag, msg, throwable)
            Arbor.WTF -> Log.wtf(tag, msg, throwable)
        }
    }

}
