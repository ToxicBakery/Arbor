package com.toxicbakery.logging

@Suppress("TooManyFunctions")
class Branch internal constructor(seedlings: Set<ISeedling> = setOf()) {

    private val seedlings: MutableSet<ISeedling> = seedlings.toMutableSet()

    internal val forest: Set<ISeedling>
        get() = seedlings.toSet()

    internal fun tag(tag: String) = forest
        .map { TaggedSeedling(tag, it) }
        .toSet()
        .let(::Branch)

    internal fun sow(seedling: ISeedling) {
        seedlings.add(seedling)
    }

    internal fun harvest(seedling: ISeedling) {
        seedlings.remove(seedling)
    }

    internal fun reset() = seedlings.clear()

    fun d(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.DEBUG, seedling.tag, msg) }
    fun d(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.DEBUG, seedling.tag, msg, throwable) }

    fun v(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.VERBOSE, seedling.tag, msg) }
    fun v(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.VERBOSE, seedling.tag, msg, throwable) }

    fun i(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.INFO, seedling.tag, msg) }
    fun i(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.INFO, seedling.tag, msg, throwable) }

    fun w(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.WARNING, seedling.tag, msg) }
    fun w(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.WARNING, seedling.tag, msg, throwable) }

    fun e(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.ERROR, seedling.tag, msg) }
    fun e(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.ERROR, seedling.tag, msg, throwable) }

    fun wtf(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.WTF, seedling.tag, msg) }
    fun wtf(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.WTF, seedling.tag, msg, throwable) }

}
