package com.toxicbakery.logging

/**
 * An instance of the Arbor API representing the state of the forest.
 */
@Suppress("TooManyFunctions")
class Branch internal constructor(seedlings: Set<ISeedling> = setOf()) {

    private val seedlings: MutableSet<ISeedling> = seedlings.toMutableSet()

    /**
     * Immutable set of all sown seedlings in the forest.
     */
    internal val forest: Set<ISeedling>
        get() = seedlings.toSet()

    /**
     * Create a tagged seedling. Tags use a shallow copy snapshot of the current forest for all logging operations.
     */
    internal fun tag(tag: String) = forest
        .map { TaggedSeedling(tag, it) }
        .toSet()
        .let(::Branch)

    /**
     * Sow a seedling into the forest. Logs will call seedlings in the order they have been sown.
     */
    internal fun sow(seedling: ISeedling) {
        seedlings.add(seedling)
    }

    /**
     * Harvest a seedling from the forest.
     */
    internal fun harvest(seedling: ISeedling) {
        seedlings.remove(seedling)
    }

    /**
     * Clear the forest of all sown seedlings returning [Arbor] to its natural state.
     */
    internal fun reset() = seedlings.clear()

    /**
     * Log a debug message.
     */
    fun d(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.DEBUG, seedling.tag, msg) }

    /**
     * Log a debug message.
     */
    fun d(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.DEBUG, seedling.tag, msg, throwable) }

    /**
     * Log a debug message.
     */
    fun d(msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.DEBUG, seedling.tag, msg, null, args) }

    /**
     * Log a debug message.
     */
    fun d(throwable: Throwable, msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.DEBUG, seedling.tag, msg, throwable, args) }

    /**
     * Log a verbose message.
     */
    fun v(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.VERBOSE, seedling.tag, msg) }

    /**
     * Log a verbose message.
     */
    fun v(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.VERBOSE, seedling.tag, msg, throwable) }

    /**
     * Log a verbose message.
     */
    fun v(msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.VERBOSE, seedling.tag, msg, null, args) }

    /**
     * Log a verbose message.
     */
    fun v(throwable: Throwable, msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.VERBOSE, seedling.tag, msg, throwable, args) }

    /**
     * Log an info message.
     */
    fun i(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.INFO, seedling.tag, msg) }

    /**
     * Log an info message.
     */
    fun i(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.INFO, seedling.tag, msg, throwable) }

    /**
     * Log a info message.
     */
    fun i(msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.INFO, seedling.tag, msg, null, args) }

    /**
     * Log a info message.
     */
    fun i(throwable: Throwable, msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.INFO, seedling.tag, msg, throwable, args) }

    /**
     * Log a warning message.
     */
    fun w(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.WARNING, seedling.tag, msg) }

    /**
     * Log a warning message.
     */
    fun w(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.WARNING, seedling.tag, msg, throwable) }

    /**
     * Log a warning message.
     */
    fun w(msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.WARNING, seedling.tag, msg, null, args) }

    /**
     * Log a warning message.
     */
    fun w(throwable: Throwable, msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.WARNING, seedling.tag, msg, throwable, args) }

    /**
     * Log an error message.
     */
    fun e(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.ERROR, seedling.tag, msg) }

    /**
     * Log an error message.
     */
    fun e(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.ERROR, seedling.tag, msg, throwable) }

    /**
     * Log a error message.
     */
    fun e(msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.ERROR, seedling.tag, msg, null, args) }

    /**
     * Log a error message.
     */
    fun e(throwable: Throwable, msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.ERROR, seedling.tag, msg, throwable, args) }

    /**
     * Log a wtf message.
     */
    fun wtf(msg: String) = seedlings.forEach { seedling -> seedling.log(Arbor.WTF, seedling.tag, msg) }

    /**
     * Log a wtf message.
     */
    fun wtf(throwable: Throwable, msg: String = "") =
        seedlings.forEach { seedling -> seedling.log(Arbor.WTF, seedling.tag, msg, throwable) }

    /**
     * Log a wtf message.
     */
    fun wtf(msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.WTF, seedling.tag, msg, null, args) }

    /**
     * Log a wtf message.
     */
    fun wtf(throwable: Throwable, msg: String, vararg args: Any) =
        seedlings.forEach { seedling -> seedling.log(Arbor.WTF, seedling.tag, msg, throwable, args) }

}
