package com.toxicbakery.logging

import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter

class LogCatSeedling : ISeedling {

    override val tag: String?
        get() = Exception().stackTrace
            .let { trace ->
                if (trace.size <= CALL_STACK_INDEX) throw IllegalStateException(INVALID_STACK)
                else trace[CALL_STACK_INDEX].className
                    .split('.')
                    .last()
            }

    override fun log(level: Int, tag: String?, msg: String, throwable: Throwable?) {
        when (level) {
            Arbor.DEBUG -> throwable?.let { Log.d(tag, msg, it) } ?: Log.d(tag, msg)
            Arbor.ERROR -> throwable?.let { Log.e(tag, msg, it) } ?: Log.e(tag, msg)
            Arbor.INFO -> throwable?.let { Log.i(tag, msg, it) } ?: Log.i(tag, msg)
            Arbor.VERBOSE -> throwable?.let { Log.v(tag, msg, it) } ?: Log.v(tag, msg)
            Arbor.WARNING -> throwable?.let { Log.w(tag, msg, it) } ?: Log.w(tag, msg)
            Arbor.WTF -> throwable?.let { Log.wtf(tag, msg, it) } ?: Log.wtf(tag, msg)
            else -> throw Exception("Unsupported log level $level")
        }
    }

    companion object {
        private const val CALL_STACK_INDEX = 1
        private const val INVALID_STACK = "Synthetic stacktrace didn't have enough elements: are you using proguard?"
    }

}

private val Throwable?.traceToString: String
    get() = StringWriter()
        .use { stringWriter -> this?.printStackTrace(PrintWriter(stringWriter)) }
        .toString()
