package com.toxicbakery.logging

import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.native.concurrent.ThreadLocal

/**
 * Primary interface to sow, harvest, and perform logging actions against seedlings. The implementation is no op by
 * default requiring a seedling to be sown for end user usage.
 */
@Suppress("TooManyFunctions")
class Arbor {

    @ThreadLocal
    companion object {
        const val DEBUG = 1
        const val VERBOSE = 2
        const val INFO = 3
        const val WARNING = 4
        const val ERROR = 5
        const val WTF = 6

        private val perennial = Branch()

        /**
         * Immutable set of all sown seedlings in the forest.
         */
        @JvmStatic
        val forest: Set<ISeedling>
            get() = perennial.forest

        /**
         * Create a tagged seedling. Tags use a shallow copy snapshot of the current forest for all logging operations.
         */
        @JvmStatic
        fun tag(tag: String): Branch = perennial.tag(tag)

        /**
         * Sow a seedling into the forest. Logs will call seedlings in the order they have been sown.
         */
        @JvmStatic
        fun sow(seedling: ISeedling) = perennial.sow(seedling)

        /**
         * Harvest a seedling from the forest.
         */
        @JvmStatic
        fun harvest(seedling: ISeedling) = perennial.harvest(seedling)

        /**
         * Clear the forest of all sown seedlings returning [Arbor] to its natural state.
         */
        @JvmStatic
        fun reset() = perennial.reset()

        /**
         * Log a debug message.
         */
        @JvmStatic
        fun d(msg: String) = perennial.d(msg)

        /**
         * Log a debug message.
         */
        @JvmStatic
        @JvmOverloads
        fun d(throwable: Throwable, msg: String = "") = perennial.d(throwable, msg)

        /**
         * Log a debug message.
         */
        @JvmStatic
        fun d(msg: String, vararg args: Any) = perennial.d(msg, args)

        /**
         * Log a debug message.
         */
        @JvmStatic
        fun d(throwable: Throwable, msg: String, vararg args: Any) = perennial.d(throwable, msg, args)

        /**
         * Log a verbose message.
         */
        @JvmStatic
        fun v(msg: String) = perennial.v(msg)

        /**
         * Log a verbose message.
         */
        @JvmStatic
        @JvmOverloads
        fun v(throwable: Throwable, msg: String = "") = perennial.v(throwable, msg)

        /**
         * Log a verbose message.
         */
        @JvmStatic
        fun v(msg: String, vararg args: Any) = perennial.v(msg, args)

        /**
         * Log a verbose message.
         */
        @JvmStatic
        fun v(throwable: Throwable, msg: String, vararg args: Any) = perennial.v(throwable, msg, args)

        /**
         * Log an info message.
         */
        @JvmStatic
        fun i(msg: String) = perennial.i(msg)

        /**
         * Log an info message.
         */
        @JvmStatic
        @JvmOverloads
        fun i(throwable: Throwable, msg: String = "") = perennial.i(throwable, msg)

        /**
         * Log a info message.
         */
        @JvmStatic
        fun i(msg: String, vararg args: Any) = perennial.i(msg, args)

        /**
         * Log a info message.
         */
        @JvmStatic
        fun i(throwable: Throwable, msg: String, vararg args: Any) = perennial.i(throwable, msg, args)

        /**
         * Log a warning message.
         */
        @JvmStatic
        fun w(msg: String) = perennial.w(msg)

        /**
         * Log a warning message.
         */
        @JvmStatic
        @JvmOverloads
        fun w(throwable: Throwable, msg: String = "") = perennial.w(throwable, msg)

        /**
         * Log a warning message.
         */
        @JvmStatic
        fun w(msg: String, vararg args: Any) = perennial.w(msg, args)

        /**
         * Log a warning message.
         */
        @JvmStatic
        fun w(throwable: Throwable, msg: String, vararg args: Any) = perennial.w(throwable, msg, args)

        /**
         * Log an error message.
         */
        @JvmStatic
        fun e(msg: String) = perennial.e(msg)

        /**
         * Log an error message.
         */
        @JvmStatic
        @JvmOverloads
        fun e(throwable: Throwable, msg: String = "") = perennial.e(throwable, msg)

        /**
         * Log a error message.
         */
        @JvmStatic
        fun e(msg: String, vararg args: Any) = perennial.e(msg, args)

        /**
         * Log a error message.
         */
        @JvmStatic
        fun e(throwable: Throwable, msg: String, vararg args: Any) = perennial.e(throwable, msg, args)

        /**
         * Log a wtf message.
         */
        @JvmStatic
        fun wtf(msg: String) = perennial.wtf(msg)

        /**
         * Log a wtf message.
         */
        @JvmStatic
        @JvmOverloads
        fun wtf(throwable: Throwable, msg: String = "") = perennial.wtf(throwable, msg)

        /**
         * Log a wtf message.
         */
        @JvmStatic
        fun wtf(msg: String, vararg args: Any) = perennial.wtf(msg, args)

        /**
         * Log a wtf message.
         */
        @JvmStatic
        fun wtf(throwable: Throwable, msg: String, vararg args: Any) = perennial.wtf(throwable, msg, args)

    }

}
