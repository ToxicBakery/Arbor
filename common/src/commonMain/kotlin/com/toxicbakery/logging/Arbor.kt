package com.toxicbakery.logging

import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.native.concurrent.ThreadLocal

/**
 * Primary interface to sow, harvest, and perform logging actions against seedlings. The implementation is no op by
 * default requiring a seedling to be sown for end user usage.
 */
@Suppress("TooManyFunctions")
@ThreadLocal
object Arbor {

    /**
     * Debug level message
     */
    const val DEBUG = 1

    /**
     * Verbose level message
     */
    const val VERBOSE = 2

    /**
     * Info level message
     */
    const val INFO = 3

    /**
     * Warning level message
     */
    const val WARNING = 4

    /**
     * Error level message
     */
    const val ERROR = 5

    /**
     * WTF level message
     */
    const val WTF = 6

    private val taglessBranch: Branch = Branch()
    private val perennial: MutableSet<ISeedling> = mutableSetOf()

    /**
     * Immutable set of all sown seedlings in the forest.
     */
    @JvmStatic
    val forest: Set<ISeedling>
        get() = perennial

    /**
     * Create a tagged seedling. Tags use a shallow copy snapshot of the current forest for all logging operations.
     */
    @JvmStatic
    fun tag(tag: String): Branch = Branch(tag)

    /**
     * Sow a seedling into the forest. Logs will call seedlings in the order they have been sown.
     *
     * @return true if added, false if previously added
     */
    @JvmStatic
    fun sow(seedling: ISeedling) = perennial.add(seedling)

    /**
     * Harvest a seedling from the forest.
     *
     * @return true if removed
     */
    @JvmStatic
    fun harvest(seedling: ISeedling) = perennial.remove(seedling)

    /**
     * Clear the forest of all sown seedlings returning [Arbor] to its natural state.
     */
    @JvmStatic
    fun reset() = perennial.clear()

    /**
     * Log a debug message.
     */
    @JvmStatic
    fun d(msg: String) = taglessBranch.d(msg)

    /**
     * Log a debug message.
     */
    @JvmStatic
    fun d(msg: () -> String) = taglessBranch.d(msg)

    /**
     * Log a debug message.
     */
    @JvmStatic
    @JvmOverloads
    fun d(throwable: Throwable, msg: String = "") = taglessBranch.d(throwable, msg)

    /**
     * Log a debug message.
     */
    @JvmStatic
    fun d(msg: String, vararg args: Any?) = taglessBranch.d(msg, args)

    /**
     * Log a debug message.
     */
    @JvmStatic
    fun d(throwable: Throwable, msg: String, vararg args: Any?) = taglessBranch.d(throwable, msg, args)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    fun v(msg: String) = taglessBranch.v(msg)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    fun v(msg: () -> String) = taglessBranch.v(msg)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    @JvmOverloads
    fun v(throwable: Throwable, msg: String = "") = taglessBranch.v(throwable, msg)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    fun v(msg: String, vararg args: Any?) = taglessBranch.v(msg, args)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    fun v(throwable: Throwable, msg: String, vararg args: Any?) = taglessBranch.v(throwable, msg, args)

    /**
     * Log an info message.
     */
    @JvmStatic
    fun i(msg: String) = taglessBranch.i(msg)

    /**
     * Log an info message.
     */
    @JvmStatic
    fun i(msg: () -> String) = taglessBranch.i(msg)

    /**
     * Log an info message.
     */
    @JvmStatic
    @JvmOverloads
    fun i(throwable: Throwable, msg: String = "") = taglessBranch.i(throwable, msg)

    /**
     * Log a info message.
     */
    @JvmStatic
    fun i(msg: String, vararg args: Any?) = taglessBranch.i(msg, args)

    /**
     * Log a info message.
     */
    @JvmStatic
    fun i(throwable: Throwable, msg: String, vararg args: Any?) = taglessBranch.i(throwable, msg, args)

    /**
     * Log a warning message.
     */
    @JvmStatic
    fun w(msg: String) = taglessBranch.w(msg)

    /**
     * Log a warning message.
     */
    @JvmStatic
    fun w(msg: () -> String) = taglessBranch.w(msg)

    /**
     * Log a warning message.
     */
    @JvmStatic
    @JvmOverloads
    fun w(throwable: Throwable, msg: String = "") = taglessBranch.w(throwable, msg)

    /**
     * Log a warning message.
     */
    @JvmStatic
    fun w(msg: String, vararg args: Any?) = taglessBranch.w(msg, args)

    /**
     * Log a warning message.
     */
    @JvmStatic
    fun w(throwable: Throwable, msg: String, vararg args: Any?) = taglessBranch.w(throwable, msg, args)

    /**
     * Log an error message.
     */
    @JvmStatic
    fun e(msg: String) = taglessBranch.e(msg)

    /**
     * Log an error message.
     */
    @JvmStatic
    fun e(msg: () -> String) = taglessBranch.e(msg)

    /**
     * Log an error message.
     */
    @JvmStatic
    @JvmOverloads
    fun e(throwable: Throwable, msg: String = "") = taglessBranch.e(throwable, msg)

    /**
     * Log a error message.
     */
    @JvmStatic
    fun e(msg: String, vararg args: Any?) = taglessBranch.e(msg, args)

    /**
     * Log a error message.
     */
    @JvmStatic
    fun e(throwable: Throwable, msg: String, vararg args: Any?) = taglessBranch.e(throwable, msg, args)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    fun wtf(msg: String) = taglessBranch.wtf(msg)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    fun wtf(msg: () -> String) = taglessBranch.wtf(msg)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    @JvmOverloads
    fun wtf(throwable: Throwable, msg: String = "") = taglessBranch.wtf(throwable, msg)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    fun wtf(msg: String, vararg args: Any?) = taglessBranch.wtf(msg, args)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    fun wtf(throwable: Throwable, msg: String, vararg args: Any?) = taglessBranch.wtf(throwable, msg, args)

}

enum class LogLevel { D, V, I, W, E, WTF }

private val defaultBranch: Branch = Arbor.tag("2143fef6-18e0-4341-a1de-b043ba5e32cd")

fun arbor(
    logLevel: LogLevel = LogLevel.D,
    tag: Branch = defaultBranch,
    message: () -> String
) = if (tag == defaultBranch) {
    when (logLevel) {
        LogLevel.D -> Arbor.d(message)
        LogLevel.V -> Arbor.v(message)
        LogLevel.I -> Arbor.i(message)
        LogLevel.W -> Arbor.w(message)
        LogLevel.E -> Arbor.e(message)
        LogLevel.WTF -> Arbor.wtf(message)
    }
} else {
    when (logLevel) {
        LogLevel.D -> tag.d(message)
        LogLevel.V -> tag.v(message)
        LogLevel.I -> tag.i(message)
        LogLevel.W -> tag.w(message)
        LogLevel.E -> tag.e(message)
        LogLevel.WTF -> tag.wtf(message)
    }
}
