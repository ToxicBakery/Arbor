package com.toxicbakery.logging

import android.os.Build
import android.util.Log
import kotlin.math.min

/**
 * General purpose logging for Android applications with auto tagging and long log wrapping. Implementation avoids the
 * log size hard limit of Android and uses and efficient right index lookup to attempt splits on existing new lines
 * before forcing hard breaks. Tagging is automatically implemented by using an exception stack trace to determine
 * the calling code.
 */
class LogCatSeedling(
    private val treatAsAndroidN: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
) : ISeedling {

    private val String.asAndroidTag: String
        get() = if (treatAsAndroidN || length < MAX_ANDROID_TAG) this
        else substring(0, MAX_ANDROID_TAG)

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
                    trace[2].className == Arbor::class.java.name -> CALL_STACK_INDEX_JAVA_CALLER
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
    @Suppress("SpreadOperator")
    override fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?,
        args: Array<out Any?>?
    ) {
        require(level >= Arbor.DEBUG && level <= Arbor.WTF)
        log(
            level = level,
            tag = tag.asAndroidTag,
            msg = if (args == null) msg else msg.format(*args),
            throwable = throwable
        )
    }

    private fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?
    ) = if (throwable == null) {
        if (msg.length <= MAX_ANDROID_MSG) writeLog(level, tag, msg)
            else msg.splitAndLog(level)
    } else msg.splitAndLog(level, throwable)

    private fun String.splitAndLog(
        level: Int,
        throwable: Throwable? = null
    ) = logCatSplit()
        .forEachIndexed { idx, messageChunk ->
            if (idx != 0 || throwable == null) writeLog(level, tag, messageChunk)
            else writeLog(level, tag, messageChunk, throwable)
        }

    companion object {
        private const val CALL_STACK_INDEX_DIRECT_CALLER = 1
        private const val CALL_STACK_INDEX_JAVA_CALLER = 3
        private const val INVALID_STACK = "Synthetic stacktrace didn't have enough elements: are you using proguard?"
        private const val MAX_ANDROID_TAG = 23

        /**
         * The max limit stated by Jake W. Looking at the Logcat source it appears the max varies by OS level but it's
         * not clear what the common minimum max value is or where this number came from. Best I can tell the minimum is
         * actually a few dozen bytes more than this but again, that's unclear.
         */
        internal const val MAX_ANDROID_MSG = 4023

        @JvmStatic
        internal fun writeLog(level: Int, tag: String, msg: String) {
            require(level >= Arbor.DEBUG && level <= Arbor.WTF)
            when (level) {
                Arbor.DEBUG -> Log.d(tag, msg)
                Arbor.ERROR -> Log.e(tag, msg)
                Arbor.INFO -> Log.i(tag, msg)
                Arbor.VERBOSE -> Log.v(tag, msg)
                Arbor.WARNING -> Log.w(tag, msg)
                Arbor.WTF -> Log.wtf(tag, msg)
            }
        }

        @JvmStatic
        internal fun writeLog(level: Int, tag: String, msg: String, throwable: Throwable) {
            require(level >= Arbor.DEBUG && level <= Arbor.WTF)
            when (level) {
                Arbor.DEBUG -> Log.d(tag, msg, throwable)
                Arbor.ERROR -> Log.e(tag, msg, throwable)
                Arbor.INFO -> Log.i(tag, msg, throwable)
                Arbor.VERBOSE -> Log.v(tag, msg, throwable)
                Arbor.WARNING -> Log.w(tag, msg, throwable)
                Arbor.WTF -> Log.wtf(tag, msg, throwable)
            }
        }

        /**
         * Split a log message at the [MAX_ANDROID_MSG] length limit.
         */
        @JvmStatic
        internal fun String.logCatSplit(): List<String> = mutableListOf<String>().also { output ->
            var leftIndex = 0
            while (leftIndex < length) {
                val rightIndex = leftIndex + MAX_ANDROID_MSG
                val block = substring(leftIndex, min(rightIndex, length))
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
