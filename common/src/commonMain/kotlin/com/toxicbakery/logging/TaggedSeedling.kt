package com.toxicbakery.logging

internal class TaggedSeedling(
    override val tag: String,
    private val wrapped: ISeedling
) : ISeedling {

    override fun log(level: Int, tag: String, msg: String, throwable: Throwable?) =
        wrapped.log(level, this.tag, msg, throwable)

}
