package com.toxicbakery.logging

/**
 * The root logging interface.
 */
public interface ISeedling {

    /**
     * A static or dynamically generated tag for logging.
     */
    public val tag: String

    /**
     * A request to perform logging.
     */
    public fun log(
        level: Int,
        tag: String = "",
        msg: String,
        throwable: Throwable? = null,
        args: Array<out Any?>? = null
    )

    /**
     * A request to perform logging.
     */
    public fun log(
        level: Int,
        tag: String,
        msg: () -> String,
        throwable: Throwable? = null,
    ): Unit = log(level, tag, msg(), throwable)

}
