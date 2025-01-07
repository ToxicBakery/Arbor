package com.toxicbakery.logging

/**
 * The root logging interface.
 */
interface ISeedling {

    /**
     * A static or dynamically generated tag for logging.
     */
    val tag: String

    /**
     * A request to perform logging.
     */
    fun log(
        level: Int,
        tag: String = "",
        msg: String,
        throwable: Throwable? = null,
        args: Array<out Any?>? = null
    )

    /**
     * A request to perform logging.
     */
    fun log(
        level: Int,
        tag: String,
        msg: () -> String,
        throwable: Throwable? = null,
    ) = log(level, tag, msg())

}
