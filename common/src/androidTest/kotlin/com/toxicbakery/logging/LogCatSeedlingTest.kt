package com.toxicbakery.logging

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class LogCatSeedlingTest {

    private lateinit var seedling: ISeedling

    @Before
    fun setup() {
        seedling = LogCatSeedling()
    }

    @Test
    fun getTag() {
        assertEquals("LogCatSeedlingTest", seedling.tag)
    }

    @Test
    fun log() {
        seedling.log(Arbor.DEBUG, "tag", "msg")
        seedling.log(Arbor.ERROR, "tag", "msg")
        seedling.log(Arbor.INFO, "tag", "msg")
        seedling.log(Arbor.VERBOSE, "tag", "msg")
        seedling.log(Arbor.WARNING, "tag", "msg")
        seedling.log(Arbor.WTF, "tag", "msg")

        seedling.log(Arbor.DEBUG, "tag", "msg", Exception("exception"))
        seedling.log(Arbor.ERROR, "tag", "msg", Exception("exception"))
        seedling.log(Arbor.INFO, "tag", "msg", Exception("exception"))
        seedling.log(Arbor.VERBOSE, "tag", "msg", Exception("exception"))
        seedling.log(Arbor.WARNING, "tag", "msg", Exception("exception"))
        seedling.log(Arbor.WTF, "tag", "msg", Exception("exception"))
    }

    @Test(expected = Exception::class)
    fun log_unsupported() {
        seedling.log(Int.MAX_VALUE, "tag", "msg")
    }

}