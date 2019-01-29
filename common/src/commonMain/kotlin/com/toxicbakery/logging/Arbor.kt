package com.toxicbakery.logging

import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

@Suppress("TooManyFunctions")
class Arbor {

    companion object {
        const val DEBUG = 1
        const val VERBOSE = 2
        const val INFO = 3
        const val WARNING = 4
        const val ERROR = 5
        const val WTF = 6

        private val perennial = Branch()

        @JvmStatic
        val forest: Set<ISeedling>
            get() = perennial.forest

        @JvmStatic
        fun tag(tag: String): Branch = perennial.tag(tag)

        @JvmStatic
        fun sow(seedling: ISeedling) = perennial.sow(seedling)

        @JvmStatic
        fun harvest(seedling: ISeedling) = perennial.harvest(seedling)

        @JvmStatic
        fun reset() = perennial.reset()

        @JvmStatic
        fun d(msg: String) = perennial.d(msg)

        @JvmStatic
        @JvmOverloads
        fun d(throwable: Throwable, msg: String = "") = perennial.d(throwable, msg)

        @JvmStatic
        fun v(msg: String) = perennial.v(msg)

        @JvmStatic
        @JvmOverloads
        fun v(throwable: Throwable, msg: String = "") = perennial.v(throwable, msg)

        @JvmStatic
        fun i(msg: String) = perennial.i(msg)

        @JvmStatic
        @JvmOverloads
        fun i(throwable: Throwable, msg: String = "") = perennial.i(throwable, msg)

        @JvmStatic
        fun w(msg: String) = perennial.w(msg)

        @JvmStatic
        @JvmOverloads
        fun w(throwable: Throwable, msg: String = "") = perennial.w(throwable, msg)

        @JvmStatic
        fun e(msg: String) = perennial.e(msg)

        @JvmStatic
        @JvmOverloads
        fun e(throwable: Throwable, msg: String = "") = perennial.e(throwable, msg)

        @JvmStatic
        fun wtf(msg: String) = perennial.wtf(msg)

        @JvmStatic
        @JvmOverloads
        fun wtf(throwable: Throwable, msg: String = "") = perennial.wtf(throwable, msg)

    }

}
