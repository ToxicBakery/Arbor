package com.toxicbakery.logging

/**
 * A seedling that has a static tag overriding the tag defined by child seedling being wrapped by this instance.
 */
internal class TaggedSeedling(
    override val tag: String,
    private val wrapped: ISeedling
) : ISeedling {

    override fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?,
        vararg args: Any
    ) = wrapped.log(level, this.tag, msg, throwable, args)

}
