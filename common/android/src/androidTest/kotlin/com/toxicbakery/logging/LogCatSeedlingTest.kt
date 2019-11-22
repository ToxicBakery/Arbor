package com.toxicbakery.logging

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class LogCatSeedlingTest {

    private lateinit var mockedDelegate: LogDelegate

    @Before
    fun setup() {
        mockedDelegate = Mockito.mock(LogDelegate::class.java)
    }

    @Test
    fun log_asN() {
        val seedling = LogCatSeedling(
            treatAsAndroidN = true,
            logDelegate = mockedDelegate
        )
        val tag = (1..100).joinToString { "a" }
        seedling.log(Arbor.DEBUG, tag, "msg", null, null)
        verify(mockedDelegate).writeLog(Arbor.DEBUG, tag, "msg")
    }

    @Test
    fun log_asPreN() {
        val seedling = LogCatSeedling(
            treatAsAndroidN = false,
            logDelegate = mockedDelegate
        )
        val tag = (1..100).joinToString { "a" }
        seedling.log(Arbor.DEBUG, tag, "msg", null, null)
        verify(mockedDelegate).writeLog(Arbor.DEBUG, tag.substring(0, 23), "msg")
    }

    @Test
    fun log_withThrowable() {
        val seedling = LogCatSeedling(logDelegate = mockedDelegate)
        val exception = Exception()
        seedling.log(Arbor.DEBUG, "tag", "msg", exception, null)
        verify(mockedDelegate).writeLog(Arbor.DEBUG, "tag", "msg", exception)
    }

    @Test
    fun log_withArgs() {
        val seedling = LogCatSeedling(logDelegate = mockedDelegate)
        seedling.log(Arbor.DEBUG, seedling.tag, "msg %s", null, arrayOf("replacement"))
        verify(mockedDelegate).writeLog(Arbor.DEBUG, seedling.tag, "msg replacement")
    }

    @Test
    fun log_withShortTag_asN() {
        val seedling = LogCatSeedling(logDelegate = mockedDelegate)
        seedling.log(Arbor.DEBUG, "tag", "msg", null, null)
        verify(mockedDelegate).writeLog(Arbor.DEBUG, "tag", "msg")
    }

    @Test
    fun log_withShortTag_asPreN() {
        val seedling = LogCatSeedling(
            treatAsAndroidN = false,
            logDelegate = mockedDelegate
        )
        seedling.log(Arbor.DEBUG, "tag", "msg", null, null)
        verify(mockedDelegate).writeLog(Arbor.DEBUG, "tag", "msg")
    }

    @Test
    fun log_withLongMessage() {
        val seedling = LogCatSeedling(logDelegate = mockedDelegate)
        val msg = (1..155).joinToString { ALPHABET }
        seedling.log(Arbor.DEBUG, seedling.tag, msg, null, null)
        val verify = verify(mockedDelegate)
        verify.writeLog(Arbor.DEBUG, seedling.tag, msg.substring(0, 4023))
        verify.writeLog(Arbor.DEBUG, seedling.tag, msg.substring(4023))
    }

    @Test
    fun log_withLongMessageAndNewLines() {
        val seedling = LogCatSeedling(logDelegate = mockedDelegate)
        val msg = (1..155).joinToString { "$ALPHABET\n" }
        seedling.log(Arbor.DEBUG, seedling.tag, msg, null, null)
        val verify = verify(mockedDelegate)
        verify.writeLog(Arbor.DEBUG, seedling.tag, (1..138).joinToString { "$ALPHABET\n" }.trim())
        verify.writeLog(Arbor.DEBUG, seedling.tag, (1..17).joinToString { "$ALPHABET\n" })
    }

    companion object {
        private const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"
    }

}