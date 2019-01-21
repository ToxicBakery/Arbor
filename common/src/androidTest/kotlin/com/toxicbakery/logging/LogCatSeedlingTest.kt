package com.toxicbakery.logging

import com.toxicbakery.logging.LogCatSeedling.Companion.MAX_ANDROID_MSG
import com.toxicbakery.logging.LogCatSeedling.Companion.logCatSplit
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

    @Test
    fun logSplitWithException() {
        seedling.log(Arbor.DEBUG, "tag", (0..4096).joinToString(separator = ""), Exception("Test"))
    }

    @Test
    fun logSplit() {
        seedling.log(Arbor.DEBUG, "tag", (0..4096).joinToString(separator = ""))
    }

    @Test(expected = Exception::class)
    fun log_unsupported() {
        seedling.log(Int.MAX_VALUE, "tag", "msg")
    }

    @Test
    fun logCatSplit() {
        val testString = (0..4096).joinToString(separator = "") { index ->
            ALPHABET[index % ALPHABET.length].toString()
        }
        assertEquals(
            listOf(
                testString.substring(0, MAX_ANDROID_MSG),
                testString.substring(MAX_ANDROID_MSG)
            ),
            testString.logCatSplit().toList()
        )
    }

    @Test
    fun logCatSplit_newLines() {
        // Repeat alphabet replacing z with new line chars. Last new line before hard max is at index 4003
        val testString = (0..4096).joinToString(separator = "") { index ->
            val output = ALPHABET[index % ALPHABET.length].toString()
            if (output == "z") "\n"
            else output
        }
        assertEquals(
            listOf(
                testString.substring(0, 4003),
                testString.substring(4003)
            ),
            testString.logCatSplit().toList()
        )
    }

    companion object {
        private const val ALPHABET: String = "abcdefghijklmnopqrstuvwxyz"
    }

}