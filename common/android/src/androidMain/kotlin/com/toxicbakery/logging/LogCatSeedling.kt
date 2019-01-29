package com.toxicbakery.logging

import android.os.Build
import android.util.Log
import com.toxicbakery.logging.Seedling.Companion.prettyPrint

/**
 * General purpose logging for Android applications with auto tagging and long log wrapping. Implementation avoids the
 * log size hard limit of Android and uses and efficient right index lookup to attempt splits on existing new lines
 * before forcing hard breaks. Tagging is automatically implemented by using an exception stack trace to determine
 * the calling code.
 */
class LogCatSeedling : ISeedling {

    /**
     * Tag generation that uses an exception stacktrace to automatically determine the calling class.
     */
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

    /**
     * Logging implementation that automatically splits on long lines and trims tags that will not fit the hard limit
     * of older Android versions.
     */
    override fun log(level: Int, tag: String, msg: String, throwable: Throwable?) {
        val tt = if (IS_AT_LEAST_N || tag.length < MAX_ANDROID_TAG) tag else tag.substring(0, MAX_ANDROID_TAG)
        if (throwable == null) {
            if (msg.length <= MAX_ANDROID_MSG) writeLog(level, tt, msg, throwable)
            else msg.splitAndLog(level)
        } else "$msg\n${throwable.prettyPrint()}".splitAndLog(level)
    }

    private fun String.splitAndLog(level: Int) = logCatSplit()
        .forEach { messageChunk -> writeLog(level, tag, messageChunk, null) }

    @Suppress("ComplexMethod")
    internal fun writeLog(level: Int, tag: String, msg: String, throwable: Throwable?): Int =
        when (level) {
            Arbor.DEBUG -> if (throwable == null) Log.d(tag, msg) else Log.d(tag, msg, throwable)
            Arbor.ERROR -> if (throwable == null) Log.e(tag, msg) else Log.e(tag, msg, throwable)
            Arbor.INFO -> if (throwable == null) Log.i(tag, msg) else Log.i(tag, msg, throwable)
            Arbor.VERBOSE -> if (throwable == null) Log.v(tag, msg) else Log.v(tag, msg, throwable)
            Arbor.WARNING -> if (throwable == null) Log.w(tag, msg) else Log.w(tag, msg, throwable)
            Arbor.WTF -> if (throwable == null) Log.wtf(tag, msg) else Log.wtf(tag, msg, throwable)
            else -> throw LoggingException("Unsupported log level $level")
        }

    companion object {
        private const val CALL_STACK_INDEX_DIRECT_CALLER = 1
        private const val CALL_STACK_INDEX_KOTLIN_CALLER = 3
        private const val CALL_STACK_INDEX_JAVA_CALLER = 4
        private const val INVALID_STACK = "Synthetic stacktrace didn't have enough elements: are you using proguard?"
        private const val MAX_ANDROID_TAG = 23

        /**
         * The max limit stated by Jake W. Looking at the Logcat source it appears the max varies by OS level but it's
         * not clear what the common minimum max value is or where this number came from. Best I can tell the minimum is
         * actually a few dozen bytes more than this but again, that's unclear.
         */
        internal const val MAX_ANDROID_MSG = 4023
        private val IS_AT_LEAST_N: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

        /**
         * Split a log message at the [MAX_ANDROID_MSG] length limit.
         */
        internal fun String.logCatSplit(): List<String> = mutableListOf<String>().also { output ->
            var leftIndex = 0
            while (leftIndex < length) {
                val rightIndex = leftIndex + MAX_ANDROID_MSG
                val block = substring(leftIndex, if (rightIndex > length) length else rightIndex)
                if (block.length < MAX_ANDROID_MSG) {
                    output.add(block)
                    leftIndex = length
                } else {
                    val lastLineBreak = block.lastIndexOf("\n")
                    leftIndex += if (lastLineBreak == -1) {
                        output.add(block)
                        block.length
                    } else {
                        output.add(block.substring(0, lastLineBreak))
                        lastLineBreak + 1
                    }
                }
            }
        }
    }

}
