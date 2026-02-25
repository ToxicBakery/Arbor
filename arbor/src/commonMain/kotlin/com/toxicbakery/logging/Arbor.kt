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
public object Arbor {

    /**
     * Debug level message
     */
    public const val DEBUG: Int = 1

    /**
     * Verbose level message
     */
    public const val VERBOSE: Int = 2

    /**
     * Info level message
     */
    public const val INFO: Int = 3

    /**
     * Warning level message
     */
    public const val WARNING: Int = 4

    /**
     * Error level message
     */
    public const val ERROR: Int = 5

    /**
     * WTF level message
     */
    public const val WTF: Int = 6

    private val taglessBranch: Branch = Branch()
    private val perennial: MutableSet<ISeedling> = mutableSetOf()

    /**
     * Immutable set of all sown seedlings in the forest.
     */
    @JvmStatic
    public val forest: Set<ISeedling>
        get() = perennial

    /**
     * Create a tagged seedling.
     */
    @JvmStatic
    public fun tag(tag: String): Branch = Branch(tag)

    /**
     * Sow a seedling into the forest. Logs will call seedlings in the order they have been sown.
     *
     * @return true if added, false if previously added
     */
    @JvmStatic
    public fun sow(seedling: ISeedling): Boolean = perennial.add(seedling)

    /**
     * Harvest a seedling from the forest.
     *
     * @return true if removed
     */
    @JvmStatic
    public fun harvest(seedling: ISeedling): Boolean = perennial.remove(seedling)

    /**
     * Clear the forest of all sown seedlings returning [Arbor] to its natural state.
     */
    @JvmStatic
    public fun reset(): Unit = perennial.clear()

    /**
     * Log a debug message.
     */
    @JvmStatic
    public fun d(msg: String): Unit = taglessBranch.d(msg)

    /**
     * Log a debug message.
     */
    @JvmStatic
    public fun d(msg: () -> String): Unit = taglessBranch.d(msg)

    /**
     * Log a debug message.
     */
    @JvmStatic
    public fun d(throwable: Throwable, msg: () -> String): Unit = taglessBranch.d(throwable, msg)

    /**
     * Log a debug message.
     */
    @JvmStatic
    @JvmOverloads
    public fun d(throwable: Throwable, msg: String = ""): Unit = taglessBranch.d(throwable, msg)

    /**
     * Log a debug message.
     */
    @JvmStatic
    public fun d(msg: String, vararg args: Any?): Unit = taglessBranch.d(msg, args)

    /**
     * Log a debug message.
     */
    @JvmStatic
    public fun d(throwable: Throwable, msg: String, vararg args: Any?): Unit = taglessBranch.d(throwable, msg, args)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    public fun v(msg: String): Unit = taglessBranch.v(msg)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    public fun v(msg: () -> String): Unit = taglessBranch.v(msg)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    public fun v(throwable: Throwable, msg: () -> String): Unit = taglessBranch.v(throwable, msg)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    @JvmOverloads
    public fun v(throwable: Throwable, msg: String = ""): Unit = taglessBranch.v(throwable, msg)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    public fun v(msg: String, vararg args: Any?): Unit = taglessBranch.v(msg, args)

    /**
     * Log a verbose message.
     */
    @JvmStatic
    public fun v(throwable: Throwable, msg: String, vararg args: Any?): Unit = taglessBranch.v(throwable, msg, args)

    /**
     * Log an info message.
     */
    @JvmStatic
    public fun i(msg: String): Unit = taglessBranch.i(msg)

    /**
     * Log an info message.
     */
    @JvmStatic
    public fun i(msg: () -> String): Unit = taglessBranch.i(msg)

    /**
     * Log an info message.
     */
    @JvmStatic
    public fun i(throwable: Throwable, msg: () -> String): Unit = taglessBranch.i(throwable, msg)

    /**
     * Log an info message.
     */
    @JvmStatic
    @JvmOverloads
    public fun i(throwable: Throwable, msg: String = ""): Unit = taglessBranch.i(throwable, msg)

    /**
     * Log a info message.
     */
    @JvmStatic
    public fun i(msg: String, vararg args: Any?): Unit = taglessBranch.i(msg, args)

    /**
     * Log a info message.
     */
    @JvmStatic
    public fun i(throwable: Throwable, msg: String, vararg args: Any?): Unit = taglessBranch.i(throwable, msg, args)

    /**
     * Log a warning message.
     */
    @JvmStatic
    public fun w(msg: String): Unit = taglessBranch.w(msg)

    /**
     * Log a warning message.
     */
    @JvmStatic
    public fun w(msg: () -> String): Unit = taglessBranch.w(msg)

    /**
     * Log a warning message.
     */
    @JvmStatic
    public fun w(throwable: Throwable, msg: () -> String): Unit = taglessBranch.w(throwable, msg)

    /**
     * Log a warning message.
     */
    @JvmStatic
    @JvmOverloads
    public fun w(throwable: Throwable, msg: String = ""): Unit = taglessBranch.w(throwable, msg)

    /**
     * Log a warning message.
     */
    @JvmStatic
    public fun w(msg: String, vararg args: Any?): Unit = taglessBranch.w(msg, args)

    /**
     * Log a warning message.
     */
    @JvmStatic
    public fun w(throwable: Throwable, msg: String, vararg args: Any?): Unit = taglessBranch.w(throwable, msg, args)

    /**
     * Log an error message.
     */
    @JvmStatic
    public fun e(msg: String): Unit = taglessBranch.e(msg)

    /**
     * Log an error message.
     */
    @JvmStatic
    public fun e(msg: () -> String): Unit = taglessBranch.e(msg)

    /**
     * Log an error message.
     */
    @JvmStatic
    public fun e(throwable: Throwable, msg: () -> String): Unit = taglessBranch.e(throwable, msg)

    /**
     * Log an error message.
     */
    @JvmStatic
    @JvmOverloads
    public fun e(throwable: Throwable, msg: String = ""): Unit = taglessBranch.e(throwable, msg)

    /**
     * Log a error message.
     */
    @JvmStatic
    public fun e(msg: String, vararg args: Any?): Unit = taglessBranch.e(msg, args)

    /**
     * Log a error message.
     */
    @JvmStatic
    public fun e(throwable: Throwable, msg: String, vararg args: Any?): Unit = taglessBranch.e(throwable, msg, args)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    public fun wtf(msg: String): Unit = taglessBranch.wtf(msg)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    public fun wtf(msg: () -> String): Unit = taglessBranch.wtf(msg)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    public fun wtf(throwable: Throwable, msg: () -> String): Unit = taglessBranch.wtf(throwable, msg)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    @JvmOverloads
    public fun wtf(throwable: Throwable, msg: String = ""): Unit = taglessBranch.wtf(throwable, msg)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    public fun wtf(msg: String, vararg args: Any?): Unit = taglessBranch.wtf(msg, args)

    /**
     * Log a wtf message.
     */
    @JvmStatic
    public fun wtf(throwable: Throwable, msg: String, vararg args: Any?): Unit = taglessBranch.wtf(throwable, msg, args)

}

private val defaultBranch: Branch = Arbor.tag("default")

/**
 * Utility function to bypass logging to all branches. This may be useful in narrow situations such as forcing log
 * output in production to a specific branch.
 */
public fun arbor(
    logLevel: LogLevel = LogLevel.D,
    branch: Branch = defaultBranch,
    message: () -> String
): Unit = if (branch === defaultBranch) {
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
        LogLevel.D -> branch.d(message)
        LogLevel.V -> branch.v(message)
        LogLevel.I -> branch.i(message)
        LogLevel.W -> branch.w(message)
        LogLevel.E -> branch.e(message)
        LogLevel.WTF -> branch.wtf(message)
    }
}
