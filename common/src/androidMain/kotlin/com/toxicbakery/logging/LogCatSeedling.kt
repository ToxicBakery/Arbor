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
        val tt = if (IS_AT_LEAST_N || tag.length < MAX_ANDROID_TAG) tag else tag.substring(0, MAX_ANDROID_TAG)
        if (throwable == null) {
            if (msg.length <= MAX_ANDROID_MSG) writeLog(level, tt, msg, throwable)
            else msg.splitAndLog(level)
        } else "$msg\n${throwable.prettyPrint()}".splitAndLog(level)
    }

    private fun String.splitAndLog(level: Int) = logCatSplit()
        .forEach { messageChunk -> writeLog(level, tag, messageChunk, null) }

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

        /**
         * The max limit stated by Jake W. Looking at the Logcat source it appears the max varies by OS level but it's
         * not clear what the common minimum max value is or where this number came from. Best I can tell the minimum is
         * actually a few dozen bytes more than this but again, that's unclear.
         */
        internal const val MAX_ANDROID_MSG = 4023
        private val IS_AT_LEAST_N: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

        internal fun Throwable.prettyPrint(): String = stackTrace
            .mapIndexed { index, stackTraceElement ->
                (if (index == 0) javaClass.name.plus(if (message == null) "\n" else ": $message\n") else "")
                    .plus("\tat $stackTraceElement")
            }
            .joinToString(separator = "\n")

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
                        lastLineBreak
                    }
                }
            }
        }
    }

}
