package com.toxicbakery.logging


class Arbor private constructor(seedlings: Set<ISeedling> = setOf()) {

    private val seedlings: MutableSet<ISeedling> = seedlings.toMutableSet()

    val forest: Set<ISeedling>
        get() = seedlings.toSet()

    fun tag(tag: String) = forest
        .map { TaggedSeedling(tag, it) }
        .toSet()
        .let(::Arbor)

    fun sow(seedling: ISeedling) {
        seedlings.add(seedling)
    }

    fun harvest(seedling: ISeedling) {
        seedlings.remove(seedling)
    }

    fun reset() = seedlings.clear()

    fun d(msg: String) = seedlings.forEach { seedling -> seedling.log(DEBUG, seedling.tag, msg) }
    fun d(throwable: Throwable, msg: String) =
        seedlings.forEach { seedling -> seedling.log(DEBUG, seedling.tag, msg, throwable) }

    fun v(msg: String) = seedlings.forEach { seedling -> seedling.log(VERBOSE, seedling.tag, msg) }
    fun v(throwable: Throwable, msg: String) =
        seedlings.forEach { seedling -> seedling.log(VERBOSE, seedling.tag, msg, throwable) }

    fun i(msg: String) = seedlings.forEach { seedling -> seedling.log(INFO, seedling.tag, msg) }
    fun i(throwable: Throwable, msg: String) =
        seedlings.forEach { seedling -> seedling.log(INFO, seedling.tag, msg, throwable) }

    fun w(msg: String) = seedlings.forEach { seedling -> seedling.log(WARNING, seedling.tag, msg) }
    fun w(throwable: Throwable, msg: String) =
        seedlings.forEach { seedling -> seedling.log(WARNING, seedling.tag, msg, throwable) }

    fun e(msg: String) = seedlings.forEach { seedling -> seedling.log(ERROR, seedling.tag, msg) }
    fun e(throwable: Throwable, msg: String) =
        seedlings.forEach { seedling -> seedling.log(ERROR, seedling.tag, msg, throwable) }

    fun wtf(msg: String) = seedlings.forEach { seedling -> seedling.log(WTF, seedling.tag, msg) }
    fun wtf(throwable: Throwable, msg: String) =
        seedlings.forEach { seedling -> seedling.log(WTF, seedling.tag, msg, throwable) }

    companion object {
        const val DEBUG = 1
        const val VERBOSE = 2
        const val INFO = 3
        const val WARNING = 4
        const val ERROR = 5
        const val WTF = 6

        private val perennial = Arbor()

        val forest: Set<ISeedling>
            get() = perennial.forest

        fun tag(tag: String) = perennial.tag(tag)
        fun sow(seedling: ISeedling) = perennial.sow(seedling)
        fun harvest(seedling: ISeedling) = perennial.harvest(seedling)
        fun reset() = perennial.reset()

        fun d(msg: String) = perennial.d(msg)
        fun d(throwable: Throwable, msg: String) = perennial.d(throwable, msg)

        fun v(msg: String) = perennial.v(msg)
        fun v(throwable: Throwable, msg: String) = perennial.v(throwable, msg)

        fun i(msg: String) = perennial.i(msg)
        fun i(throwable: Throwable, msg: String) = perennial.i(throwable, msg)

        fun w(msg: String) = perennial.w(msg)
        fun w(throwable: Throwable, msg: String) = perennial.w(throwable, msg)

        fun e(msg: String) = perennial.e(msg)
        fun e(throwable: Throwable, msg: String) = perennial.e(throwable, msg)

        fun wtf(msg: String) = perennial.wtf(msg)
        fun wtf(throwable: Throwable, msg: String) = perennial.wtf(throwable, msg)

    }

    private class TaggedSeedling(
        override val tag: String,
        private val wrapped: ISeedling
    ) : ISeedling {

        override fun log(level: Int, tag: String?, msg: String, throwable: Throwable?) =
            wrapped.log(level, this.tag, msg, throwable)

    }

}
