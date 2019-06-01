package com.toxicbakery.logging

class Seedling : ISeedling {

    override val tag: String
        get() = ""

    override fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?,
        args: Array<out Any?>?
    ) {
        require(level >= Arbor.DEBUG && level <= Arbor.WTF)
        val message = "$tag$msg"
        val trace = throwable?.trace ?: ""
        when {
            message.isEmpty() && trace.isEmpty() -> return
            message.isEmpty() -> println(trace)
            else -> println("$message\n$trace")
        }
    }

    /**
     * Print a trace of the exception by printing the message and each wrapped exceptions message.
     */
    private val Throwable.trace: String
        get() = (message ?: "") + (cause?.let { throwable -> "\n${throwable.trace}" })

}
