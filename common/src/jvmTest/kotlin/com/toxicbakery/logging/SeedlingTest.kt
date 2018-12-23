package com.toxicbakery.logging

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SeedlingTest {

    private lateinit var testSeedling: TestSeedling

    @Before
    fun setup() {
        testSeedling = TestSeedling()
        Arbor.sow(testSeedling)
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
        Arbor.d(Exception("World"), "Hello,")
        assertEquals("${Arbor.DEBUG} null Hello, World", testSeedling.log)
    }

    @Test
    fun log_withTag() {
        Arbor.tag("tag").d(Exception("World"), "Hello,")
        assertEquals("${Arbor.DEBUG} tag Hello, World", testSeedling.log)
    }

}