package com.toxicbakery.logging

import com.toxicbakery.logging.Seedling.Companion.isTopLevelArborCall
import com.toxicbakery.logging.Seedling.Companion.prettyPrint
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertFalse

class SeedlingTest {

    private lateinit var bufferOut: ByteArrayOutputStream
    private lateinit var bufferErr: ByteArrayOutputStream
    private lateinit var seedling: ISeedling
    private val defaultTag = SeedlingTest::class.java.simpleName

    @Before
    fun setup() {
        bufferOut = ByteArrayOutputStream()
        bufferErr = ByteArrayOutputStream()
        seedling = Seedling(
            printStreamErr = PrintStream(bufferErr),
            printStreamOut = PrintStream(bufferOut),
            callStackIndex = 3
        )
        Arbor.sow(seedling)
    }

    @After
    fun teardown() {
        Arbor.reset()
    }

    @Test
    fun getTag() {
        assertEquals("tag", Arbor.tag("tag").forest.first().tag)
    }

    @Test
    fun log() {
        Arbor.d("Hello, World")
        assertEquals("$defaultTag: Hello, World\n", bufferOut.toString())
    }

    @Test
    fun log_error() {
        Arbor.e("Hello, World")
        assertEquals("$defaultTag: Hello, World\n", bufferErr.toString())
    }

    @Test
    fun log_withException() {
        Arbor.d(Exception("World"), "Hello,")
        assertTrue(
            bufferOut.toString()
                .startsWith("$defaultTag: Hello, java.lang.Exception: World")
        )
    }

    @Test
    fun log_withTag() {
        Arbor.tag("tag").d("Hello, World")
        assertEquals("tag: Hello, World\n", bufferOut.toString())
    }

    @Test
    fun directLog_nullException() {
        seedling.log(Arbor.DEBUG, "tag", "msg", null)
        assertEquals("tag: msg\n", bufferOut.toString())
    }

    @Test
    fun directLog_withDefaults() {
        seedling.log(level = Arbor.DEBUG, msg = "")
        assertEquals("\n", bufferOut.toString())
    }

    @Test
    fun directLog_withTag() {
        seedling.log(level = Arbor.DEBUG, tag = "", msg = "")
        assertEquals("\n", bufferOut.toString())
    }

    @Test
    fun directLog_withTagAndException() {
        seedling.log(Arbor.DEBUG, "tag", "msg", Exception())
        assertTrue(bufferOut.toString()
            .startsWith("tag: msg java.lang.Exception"))
    }

    @Test
    fun prettyPrint_nullMsg() {
        assertTrue(Exception().prettyPrint().startsWith("java.lang.Exception"))
    }

    @Test(expected = LoggingException::class)
    fun invalidCallStack() {
        Seedling(callStackIndex = Int.MAX_VALUE).tag
    }

    @Test
    fun isTopLevelArborCall() {
        assertTrue(createStackTraceElementForClass(Arbor::class.java).isTopLevelArborCall)
        assertTrue(createStackTraceElementForClass(Arbor.Companion::class.java).isTopLevelArborCall)
        assertTrue(createStackTraceElementForClass(TaggedSeedling::class.java).isTopLevelArborCall)
        assertFalse(createStackTraceElementForClass(Exception::class.java).isTopLevelArborCall)
    }

    private fun <T> createStackTraceElementForClass(clazz: Class<T>) =
        StackTraceElement(
            clazz.name,
            "method",
            "fileName",
            0 // LineNumber
        )

}