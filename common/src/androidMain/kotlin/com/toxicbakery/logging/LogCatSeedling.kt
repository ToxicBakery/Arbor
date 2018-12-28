package com.toxicbakery.logging

import android.os.Build
import android.util.Log

class LogCatSeedling : ISeedling {

    @Suppress("MagicNumber")
    override val tag: String
        get() = Exception()
            .stackTrace
            .let { trace ->
                // Calling from Java has an extra class in the stack trace (Arbor) resulting in the wrong class name
                val stackIndex = when {
                    trace[3].className == Arbor::class.java.name -> CALL_STACK_INDEX_JAVA_CALLER
                    trace[2].className == Arbor.Companion::class.java.name -> CALL_STACK_INDEX_KOTLIN_CALLER
                    else -> CALL_STACK_INDEX_DIRECT_CALLER
                }
                if (trace.size <= stackIndex) throw LoggingException(INVALID_STACK)
                else trace[stackIndex].className
                    .split('.')
                    .last()
            }

    override fun log(level: Int, tag: String?, msg: String, throwable: Throwable?) {
        if (tag == null) throw NullPointerException("Tag must not be null")
        val tt = if (tag.length < MAX_ANDROID_TAG || IS_AT_LEAST_N) tag else tag.substring(0, MAX_ANDROID_TAG)
        writeLog(level, tt, msg, throwable)
    }

    @Suppress("ComplexMethod")
    private fun writeLog(level: Int, tag: String, msg: String, throwable: Throwable?) =
        when (level) {
            Arbor.DEBUG -> throwable?.let { Log.d(tag, msg, it) } ?: Log.d(tag, msg)
            Arbor.ERROR -> throwable?.let { Log.e(tag, msg, it) } ?: Log.e(tag, msg)
            Arbor.INFO -> throwable?.let { Log.i(tag, msg, it) } ?: Log.i(tag, msg)
            Arbor.VERBOSE -> throwable?.let { Log.v(tag, msg, it) } ?: Log.v(tag, msg)
            Arbor.WARNING -> throwable?.let { Log.w(tag, msg, it) } ?: Log.w(tag, msg)
            Arbor.WTF -> throwable?.let { Log.wtf(tag, msg, it) } ?: Log.wtf(tag, msg)
            else -> throw LoggingException("Unsupported log level $level")
        }

    companion object {
        private const val CALL_STACK_INDEX_DIRECT_CALLER = 1
        private const val CALL_STACK_INDEX_KOTLIN_CALLER = 3
        private const val CALL_STACK_INDEX_JAVA_CALLER = 4
        private const val INVALID_STACK = "Synthetic stacktrace didn't have enough elements: are you using proguard?"
        private const val MAX_ANDROID_TAG = 23
        private val IS_AT_LEAST_N: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

}
