package com.toxicbakery.logging

class Seedling : ISeedling {

    override val tag: String?
        get() = TODO("not implemented")

    override fun log(level: Int, tag: String?, msg: String, throwable: Throwable?) =
        println("$tag$msg")

}
