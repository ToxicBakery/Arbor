package com.toxicbakery.logging

import com.toxicbakery.logging.Arbor.DEBUG
import com.toxicbakery.logging.Arbor.ERROR
import com.toxicbakery.logging.Arbor.INFO
import com.toxicbakery.logging.Arbor.VERBOSE
import com.toxicbakery.logging.Arbor.WARNING
import com.toxicbakery.logging.Arbor.WTF
import platform.Foundation.NSLog

/**
 * Basic logger that prints messages to the Apple unified logging system via [NSLog]. Messages are line printed in the
 * format of `LEVEL/TAG: Message` with any [Throwable] stack trace appended.
 *
 * Positional arguments are interpolated into the message in order. As Kotlin/Native has no `String.format`, only the
 * conversion position is honored; width, precision, and flags (e.g. the `5.2` in `%5.2f`) are ignored and the argument
 * is inserted via its `toString()`.
 *
 * ```
 * Arbor.sow(Seedling())
 * ```
 */
public class Seedling internal constructor(
    private val write: (String) -> Unit,
) : ISeedling {

    // Log via NSLog without its variadic argument list: Kotlin/Native does not marshal a Kotlin String into a
    // C variadic call as an Obj-C object, so `NSLog("%@", message)` reads the string bytes as a pointer and
    // crashes. Instead pass the message as the (non-variadic, properly bridged) format string with every `%`
    // escaped to `%%`, so no conversion specifier is ever interpreted.
    public constructor() : this(write = { message -> NSLog(message.replace("%", "%%")) })

    override val tag: String
        get() = ""

    override fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?,
        args: Array<out Any?>?
    ) {
        require(level in DEBUG..WTF)
        val message = tag.withMessage(msg).interpolate(args)
        if (message.isEmpty() && throwable == null) return
        write("${level.levelLabel}$message".withThrowable(throwable))
    }

    private fun String.withMessage(msg: String): String =
        if (isEmpty()) msg
        else "$this: $msg"

    private fun String.withThrowable(throwable: Throwable?): String =
        if (throwable == null) this
        else "$this\n${throwable.stackTraceToString()}"

    /**
     * Replace each `%[flags][width][.precision]conversion` token with the next argument's string representation,
     * consuming arguments in order. `%%` yields a literal `%`. Mirrors the JVM seedling: when [args] is null or empty
     * the receiver is returned untouched, and any specifier left without an argument is emitted verbatim.
     */
    private fun String.interpolate(args: Array<out Any?>?): String {
        if (args.isNullOrEmpty()) return this
        val result = StringBuilder(length)
        var argIndex = 0
        var index = 0
        while (index < length) {
            val char = this[index]
            if (char != '%') {
                result.append(char)
                index++
                continue
            }
            // Advance past the specifier's flags/width/precision to find the conversion character.
            var end = index + 1
            while (end < length && this[end] in FORMAT_PREFIX) end++
            when {
                end >= length -> {
                    result.append(substring(index))
                    index = length
                }
                this[end] == '%' -> {
                    result.append('%')
                    index = end + 1
                }
                argIndex < args.size -> {
                    result.append(args[argIndex++].toString())
                    index = end + 1
                }
                else -> {
                    result.append(substring(index, end + 1))
                    index = end + 1
                }
            }
        }
        return result.toString()
    }

    private val Int.levelLabel: String
        get() = when (this) {
            DEBUG -> "D/"
            VERBOSE -> "V/"
            INFO -> "I/"
            WARNING -> "W/"
            ERROR -> "E/"
            WTF -> "F/"
            else -> ""
        }

    private companion object {
        // Flag, width, and precision characters that may appear between `%` and the conversion character.
        private const val FORMAT_PREFIX = "0123456789.-+ #,("
    }

}
