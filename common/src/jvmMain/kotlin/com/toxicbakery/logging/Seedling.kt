package com.toxicbakery.logging

import java.io.PrintStream

/**
 * Basic logger that prints [Error] and [WTF] messages to the [System.err] stream. All other logs are printed to the
 * [System.out] stream. Messages are line printed in the format of `TAG: Message`.
 *
 * ```
 * Arbor.sow(Seedling())
 * ```
 */
class Seedling @JvmOverloads constructor(
    private val printStreamErr: PrintStream = System.err,
    private val printStreamOut: PrintStream = System.out,
    private val callStackIndex: Int = CALL_STACK_INDEX
) : ISeedling {

    private val Array<StackTraceElement>.asTag: String
        get() = indexOfLast { element -> element.isTopLevelArborCall }
            .let { index -> if (index == -1) callStackIndex else index + 1 }
            .let { index ->
                if (size <= index) throw LoggingException(INVALID_STACK)
                else this[index].className
                    .split('.')
                    .last()
            }

    @Suppress("ThrowingExceptionsWithoutMessageOrCause")
    override val tag: String
        get() = Exception()
            .stackTrace
            .asTag

    @Suppress("SpreadOperator")
    override fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?,
        args: Array<out Any?>?
    ) {
        require(level >= Arbor.DEBUG && level <= Arbor.WTF)
        tag.withMessage(msg)
            .let { taggedMessage ->
                if (args == null || args.isEmpty()) taggedMessage
                else taggedMessage.format(*args)
            }
            .withThrowable(throwable)
            .let { message -> logMessage(message, level) }
    }

    private fun logMessage(message: String, level: Int) = when (level) {
        Arbor.ERROR,
        Arbor.WTF -> printStreamErr.println(message)
        else -> printStreamOut.println(message)
    }

    private fun String.withMessage(msg: String): String =
        if (isEmpty()) msg
        else "$this: $msg"

    private fun String.withThrowable(throwable: Throwable?) =
        if (throwable == null) this
        else "$this ${throwable.prettyPrint()}"

    companion object {
        private const val CALL_STACK_INDEX = -1
        private const val INVALID_STACK = "Synthetic stacktrace didn't have enough elements: are you using proguard?"

        @JvmStatic
        fun Throwable.prettyPrint(): String = stackTrace
            .mapIndexed { index, stackTraceElement ->
                (if (index == 0) javaClass.name.plus(if (message == null) "\n" else ": $message\n") else "")
                    .plus("\tat $stackTraceElement")
            }
            .joinToString(separator = "\n")

        @JvmStatic
        internal val StackTraceElement.isTopLevelArborCall: Boolean
            get() = className == Arbor::class.java.name

    }

}
