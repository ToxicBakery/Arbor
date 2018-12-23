package com.toxicbakery.logging

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


class SeedlingTest {

    private lateinit var testSeedling: TestSeedling

    @BeforeTest
    fun setup() {
        testSeedling = TestSeedling()
        Arbor.sow(testSeedling)
    }

    @AfterTest
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
