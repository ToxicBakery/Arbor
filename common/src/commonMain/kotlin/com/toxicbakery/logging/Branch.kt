package com.toxicbakery.logging

/**
 * An instance of the Arbor API representing the state of the forest.
 */
@Suppress("TooManyFunctions")
class Branch internal constructor(private val tag: String = "") {

    private val tagIsBlank: Boolean = tag.isBlank()

    override fun equals(other: Any?): Boolean =
        other is Branch && other.tag == tag

    override fun hashCode(): Int = tag.hashCode()

    /**
     * Log a debug message.
     */
    fun d(msg: String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a debug message.
     */
    fun d(msg: () -> String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a debug message.
     */
    fun d(throwable: Throwable, msg: String = "") = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, if (tagIsBlank) seedling.tag else tag, msg, throwable)
        }

    /**
     * Log a debug message.
     */
    fun d(msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, if (tagIsBlank) seedling.tag else tag, msg, null, args)
        }

    /**
     * Log a debug message.
     */
    fun d(throwable: Throwable, msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.DEBUG, if (tagIsBlank) seedling.tag else tag, msg, throwable, args)
        }

    /**
     * Log a verbose message.
     */
    fun v(msg: String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a verbose message.
     */
    fun v(msg: () -> String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a verbose message.
     */
    fun v(throwable: Throwable, msg: String = "") = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, if (tagIsBlank) seedling.tag else tag, msg, throwable)
        }

    /**
     * Log a verbose message.
     */
    fun v(msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, if (tagIsBlank) seedling.tag else tag, msg, null, args)
        }

    /**
     * Log a verbose message.
     */
    fun v(throwable: Throwable, msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.VERBOSE, if (tagIsBlank) seedling.tag else tag, msg, throwable, args)
        }

    /**
     * Log an info message.
     */
    fun i(msg: String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log an info message.
     */
    fun i(msg: () -> String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log an info message.
     */
    fun i(throwable: Throwable, msg: String = "") = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, if (tagIsBlank) seedling.tag else tag, msg, throwable)
        }

    /**
     * Log a info message.
     */
    fun i(msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, if (tagIsBlank) seedling.tag else tag, msg, null, args)
        }

    /**
     * Log a info message.
     */
    fun i(throwable: Throwable, msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.INFO, if (tagIsBlank) seedling.tag else tag, msg, throwable, args)
        }

    /**
     * Log a warning message.
     */
    fun w(msg: String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a warning message.
     */
    fun w(msg: () -> String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a warning message.
     */
    fun w(throwable: Throwable, msg: String = "") = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, if (tagIsBlank) seedling.tag else tag, msg, throwable)
        }

    /**
     * Log a warning message.
     */
    fun w(msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, if (tagIsBlank) seedling.tag else tag, msg, null, args)
        }

    /**
     * Log a warning message.
     */
    fun w(throwable: Throwable, msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WARNING, if (tagIsBlank) seedling.tag else tag, msg, throwable, args)
        }

    /**
     * Log an error message.
     */
    fun e(msg: String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log an error message.
     */
    fun e(msg: () -> String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log an error message.
     */
    fun e(throwable: Throwable, msg: String = "") = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, if (tagIsBlank) seedling.tag else tag, msg, throwable)
        }

    /**
     * Log a error message.
     */
    fun e(msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, if (tagIsBlank) seedling.tag else tag, msg, null, args)
        }

    /**
     * Log a error message.
     */
    fun e(throwable: Throwable, msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.ERROR, if (tagIsBlank) seedling.tag else tag, msg, throwable, args)
        }

    /**
     * Log a wtf message.
     */
    fun wtf(msg: String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a wtf message.
     */
    fun wtf(msg: () -> String) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, if (tagIsBlank) seedling.tag else tag, msg)
        }

    /**
     * Log a wtf message.
     */
    fun wtf(throwable: Throwable, msg: String = "") = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, if (tagIsBlank) seedling.tag else tag, msg, throwable)
        }

    /**
     * Log a wtf message.
     */
    fun wtf(msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, if (tagIsBlank) seedling.tag else tag, msg, null, args)
        }

    /**
     * Log a wtf message.
     */
    fun wtf(throwable: Throwable, msg: String, args: Array<out Any?>?) = Arbor.forest
        .forEach { seedling ->
            seedling.log(Arbor.WTF, if (tagIsBlank) seedling.tag else tag, msg, throwable, args)
        }

}
