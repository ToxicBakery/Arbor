package com.toxicbakery.logging

interface ISeedling {

    val tag: String

    fun log(level: Int, tag: String = "", msg: String, throwable: Throwable? = null)

}
