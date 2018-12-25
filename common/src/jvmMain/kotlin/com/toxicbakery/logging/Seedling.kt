package com.toxicbakery.logging

import java.io.PrintWriter
import java.io.StringWriter

class Seedling : ISeedling {

    override val tag: String?
        get() = Exception().stackTrace
            .let { trace ->
                if (trace.size <= CALL_STACK_INDEX) throw IllegalStateException(INVALID_STACK)
                else trace[CALL_STACK_INDEX].className
            }

    override fun log(level: Int, tag: String?, msg: String, throwable: Throwable?): Unit =
        (tag?.plus(": $msg") ?: msg)
            .plus(throwable.traceToString)
            .let { message ->
                when (level) {
                    Arbor.ERROR,
                    Arbor.WTF -> System.err.println(message)
                    else -> System.out.println(message)
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
