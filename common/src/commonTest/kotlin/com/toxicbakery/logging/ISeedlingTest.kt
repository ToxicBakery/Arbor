package com.toxicbakery.logging

import kotlin.test.Test

class ISeedlingTest {

    @Test
    fun log() {
        val seedling = object : ISeedling {
            override val tag: String = "tag"
            override fun log(
                level: Int,
                tag: String,
                msg: String,
                throwable: Throwable?,
                vararg args: Any
            ) = Unit
        }

        seedling.log(level = Arbor.DEBUG, msg = "")
        seedling.log(level = Arbor.DEBUG, tag = "", msg = "")
        seedling.log(level = Arbor.DEBUG, tag = "", msg = "", throwable = Exception())
        seedling.log(level = Arbor.DEBUG, msg = "", throwable = Exception())
    }

}
