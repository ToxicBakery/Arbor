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
        vararg args: Any
    )

}
