package com.toxicbakery.logging

data class TestSeedling(
    override val tag: String = ""
) : ISeedling {

    private val logBuffer: StringBuilder = StringBuilder()

    val log: String
        get() = logBuffer.toString()

    override fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?,
        args: Array<out Any?>?
    ) {
        val exceptionString = throwable?.message?.let { " $it" } ?: ""
        logBuffer.append("$level $tag $msg$exceptionString")
    }

}
