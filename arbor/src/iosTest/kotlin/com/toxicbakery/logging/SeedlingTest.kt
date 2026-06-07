package com.toxicbakery.logging

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SeedlingTest {

    private val output = mutableListOf<String>()
    private val seedling: ISeedling = Seedling { line -> output.add(line) }

    @BeforeTest
    fun setup() {
        Arbor.sow(seedling)
    }

    @AfterTest
    fun teardown() {
        Arbor.reset()
    }

    @Test
    fun log() {
        Arbor.d("Hello, World")
        assertEquals(listOf("D/Hello, World"), output)
    }

    @Test
    fun log_error() {
        Arbor.e("Hello, World")
        assertEquals(listOf("E/Hello, World"), output)
    }

    @Test
    fun log_allLevels() {
        Arbor.v("v")
        Arbor.i("i")
        Arbor.w("w")
        Arbor.wtf("wtf")
        assertEquals(listOf("V/v", "I/i", "W/w", "F/wtf"), output)
    }

    @Test
    fun log_withTag() {
        Arbor.tag("tag").d("Hello, World")
        assertEquals(listOf("D/tag: Hello, World"), output)
    }

    @Test
    fun log_withArgs() {
        Arbor.d("Hello, %s", "World")
        assertEquals(listOf("D/Hello, World"), output)
    }

    @Test
    fun log_withMultipleArgs() {
        Arbor.d("%s and %s", "a", "b")
        assertEquals(listOf("D/a and b"), output)
    }

    @Test
    fun log_withEmptyArgs() {
        @Suppress("RemoveRedundantSpreadOperator")
        Arbor.d("Hello, %s", *emptyArray<Any?>())
        assertEquals(listOf("D/Hello, %s"), output)
    }

    @Test
    fun log_withEscapedPercent() {
        Arbor.d("100%% done", "ignored")
        assertEquals(listOf("D/100% done"), output)
    }

    @Test
    fun log_withException() {
        seedling.log(Arbor.DEBUG, "tag", "msg", RuntimeException("boom"))
        assertEquals(1, output.size)
        assertTrue(output.first().startsWith("D/tag: msg\n"))
    }

    @Test
    fun directLog_nullException() {
        seedling.log(Arbor.DEBUG, "tag", "msg", null)
        assertEquals(listOf("D/tag: msg"), output)
    }

    @Test
    fun directLog_emptyMessageSkipped() {
        seedling.log(level = Arbor.DEBUG, msg = "")
        assertTrue(output.isEmpty())
    }

}
