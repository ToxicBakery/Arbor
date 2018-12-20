package com.toxicbakery.logging

data class TestSeedling(
    override val tag: String? = null
) : ISeedling {

    val logBuffer: StringBuilder = StringBuilder()

    override fun log(level: Int, tag: String?, msg: String, throwable: Throwable?) {
        logBuffer.append("$level $tag $msg")
    }

}
