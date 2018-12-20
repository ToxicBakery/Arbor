package com.toxicbakery.logging

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ArborTest {

    @BeforeTest
    fun setup() {
        Arbor.reset()
        assertEquals(0, Arbor.forest.size)
    }

    @Test
    fun testGetForest() {
        Arbor.sow(TestSeedling())
        assertEquals(1, Arbor.forest.size)
    }

    @Test
    fun testHarvest() {
        Arbor.sow(TestSeedling())
        Arbor.harvest(TestSeedling())
        assertEquals(0, Arbor.forest.size)
    }

    @Test
    fun testD() {
    }

    @Test
    fun testD1() {
    }

    @Test
    fun testV() {
    }

    @Test
    fun testV1() {
    }

    @Test
    fun testI() {
    }

    @Test
    fun testI1() {
    }

    @Test
    fun testW() {
    }

    @Test
    fun testW1() {
    }

    @Test
    fun testE() {
    }

    @Test
    fun testE1() {
    }

    @Test
    fun testWtf() {
    }

    @Test
    fun testWtf1() {
    }

}
