package com.toxicbakery.logging

import org.junit.Test

class LogCatSeedlingTest {

    @Test
    fun log_asN() {
        val seedling = LogCatSeedling()
        seedling.log(Arbor.DEBUG, seedling.tag, "msg", null, null)
        seedling.log(Arbor.ERROR, seedling.tag, "msg", null, null)
        seedling.log(Arbor.INFO, seedling.tag, "msg", null, null)
        seedling.log(Arbor.VERBOSE, seedling.tag, "msg", null, null)
        seedling.log(Arbor.WARNING, seedling.tag, "msg", null, null)
        seedling.log(Arbor.WTF, seedling.tag, "msg", null, null)
    }

    @Test
    fun log_asPreN() {
        val seedling = LogCatSeedling(
            treatAsAndroidN = false
        )
        seedling.log(Arbor.DEBUG, seedling.tag, "msg", null, null)
        seedling.log(Arbor.ERROR, seedling.tag, "msg", null, null)
        seedling.log(Arbor.INFO, seedling.tag, "msg", null, null)
        seedling.log(Arbor.VERBOSE, seedling.tag, "msg", null, null)
        seedling.log(Arbor.WARNING, seedling.tag, "msg", null, null)
        seedling.log(Arbor.WTF, seedling.tag, "msg", null, null)
    }

    @Test
    fun log_withThrowable() {
        val seedling = LogCatSeedling()
        seedling.log(Arbor.DEBUG, "tag", "msg", Exception(), null)
        seedling.log(Arbor.ERROR, "tag", "msg", Exception(), null)
        seedling.log(Arbor.INFO, "tag", "msg", Exception(), null)
        seedling.log(Arbor.VERBOSE, "tag", "msg", Exception(), null)
        seedling.log(Arbor.WARNING, "tag", "msg", Exception(), null)
        seedling.log(Arbor.WTF, "tag", "msg", Exception(), null)
    }

    @Test
    fun log_withArgs() {
        val seedling = LogCatSeedling()
        seedling.log(
            Arbor.DEBUG,
            seedling.tag,
            "msg %s",
            null,
            arrayOf("replacement")
        )
    }

    @Test
    fun log_withExcessiveTag_asN() {
        val seedling = LogCatSeedling()
        seedling.log(
            Arbor.DEBUG,
            (1..100).joinToString { "tag" },
            "msg",
            null,
            null
        )
    }

    @Test
    fun log_withExcessiveTag_asPreN() {
        val seedling = LogCatSeedling(
            treatAsAndroidN = false
        )
        seedling.log(
            Arbor.DEBUG,
            (1..100).joinToString { "tag" },
            "msg",
            null,
            null
        )
    }

    @Test
    fun log_withShortTag_asPreN() {
        val seedling = LogCatSeedling(
            treatAsAndroidN = false
        )
        seedling.log(
            Arbor.DEBUG,
            "tag",
            "msg",
            null,
            null
        )
    }

    @Test
    fun log_withLongMessage() {
        val seedling = LogCatSeedling()
        seedling.log(
            Arbor.DEBUG,
            seedling.tag,
            (1..5000).joinToString { "msg" },
            null,
            null
        )
    }

    @Test
    fun log_withLongMessageAndNewLines() {
        val seedling = LogCatSeedling()
        seedling.log(
            Arbor.DEBUG,
            seedling.tag,
            (1..5000).joinToString { "msg\n" },
            null,
            null
        )
    }

}