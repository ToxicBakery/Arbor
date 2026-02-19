package com.toxicbakery.logging

/**
 * An instance of the Arbor API representing the state of the forest.
 */
@Suppress("TooManyFunctions")
@ConsistentCopyVisibility
public data class Branch internal constructor(private val tag: String = "") {

    private val tagIsBlank: Boolean = tag.isBlank()

    private fun determineTag(seedling: ISeedling): String =
        if (tagIsBlank) seedling.tag else tag

    /**
     * Log a debug message.
     */
    public fun d(msg: String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, determineTag(seedling), msg)
        }

    /**
     * Log a debug message.
     */
    public fun d(msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, determineTag(seedling), msg)
        }

    /**
     * Log a debug message.
     */
    public fun d(throwable: Throwable, msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a debug message.
     */
    public fun d(throwable: Throwable, msg: String = ""): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a debug message.
     */
    public fun d(msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, determineTag(seedling), msg, null, args)
        }

    /**
     * Log a debug message.
     */
    public fun d(throwable: Throwable, msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, determineTag(seedling), msg, throwable, args)
        }

    /**
     * Log a verbose message.
     */
    public fun v(msg: String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, determineTag(seedling), msg)
        }

    /**
     * Log a verbose message.
     */
    public fun v(msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, determineTag(seedling), msg)
        }

    /**
     * Log a verbose message.
     */
    public fun v(throwable: Throwable, msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a verbose message.
     */
    public fun v(throwable: Throwable, msg: String = ""): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a verbose message.
     */
    public fun v(msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, determineTag(seedling), msg, null, args)
        }

    /**
     * Log a verbose message.
     */
    public fun v(throwable: Throwable, msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, determineTag(seedling), msg, throwable, args)
        }

    /**
     * Log an info message.
     */
    public fun i(msg: String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, determineTag(seedling), msg)
        }

    /**
     * Log an info message.
     */
    public fun i(msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, determineTag(seedling), msg)
        }

    /**
     * Log an info message.
     */
    public fun i(throwable: Throwable, msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, determineTag(seedling), msg, throwable)
        }

    /**
     * Log an info message.
     */
    public fun i(throwable: Throwable, msg: String = ""): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a info message.
     */
    public fun i(msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, determineTag(seedling), msg, null, args)
        }

    /**
     * Log a info message.
     */
    public fun i(throwable: Throwable, msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, determineTag(seedling), msg, throwable, args)
        }

    /**
     * Log a warning message.
     */
    public fun w(msg: String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, determineTag(seedling), msg)
        }

    /**
     * Log a warning message.
     */
    public fun w(msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, determineTag(seedling), msg)
        }

    /**
     * Log a warning message.
     */
    public fun w(throwable: Throwable, msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a warning message.
     */
    public fun w(throwable: Throwable, msg: String = ""): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a warning message.
     */
    public fun w(msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, determineTag(seedling), msg, null, args)
        }

    /**
     * Log a warning message.
     */
    public fun w(throwable: Throwable, msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, determineTag(seedling), msg, throwable, args)
        }

    /**
     * Log an error message.
     */
    public fun e(msg: String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, determineTag(seedling), msg)
        }

    /**
     * Log an error message.
     */
    public fun e(msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, determineTag(seedling), msg)
        }

    /**
     * Log an error message.
     */
    public fun e(throwable: Throwable, msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, determineTag(seedling), msg, throwable)
        }

    /**
     * Log an error message.
     */
    public fun e(throwable: Throwable, msg: String = ""): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a error message.
     */
    public fun e(msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, determineTag(seedling), msg, null, args)
        }

    /**
     * Log a error message.
     */
    public fun e(throwable: Throwable, msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, determineTag(seedling), msg, throwable, args)
        }

    /**
     * Log a wtf message.
     */
    public fun wtf(msg: String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, determineTag(seedling), msg)
        }

    /**
     * Log a wtf message.
     */
    public fun wtf(msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, determineTag(seedling), msg)
        }

    /**
     * Log a wtf message.
     */
    public fun wtf(throwable: Throwable, msg: () -> String): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a wtf message.
     */
    public fun wtf(throwable: Throwable, msg: String = ""): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, determineTag(seedling), msg, throwable)
        }

    /**
     * Log a wtf message.
     */
    public fun wtf(msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, determineTag(seedling), msg, null, args)
        }

    /**
     * Log a wtf message.
     */
    public fun wtf(throwable: Throwable, msg: String, args: Array<out Any?>?): Unit = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, determineTag(seedling), msg, throwable, args)
        }

}
