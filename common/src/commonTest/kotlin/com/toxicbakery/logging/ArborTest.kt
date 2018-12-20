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
    fun testTag() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.tag("tag").d("test")
        assertEquals("${Arbor.DEBUG} tag test", seedling.log)
    }

    @Test
    fun testD() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.d("d")
        assertEquals("${Arbor.DEBUG} null d", seedling.log)
    }

    @Test
    fun testD1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.d(Exception("d1"), "d1")
        assertEquals("${Arbor.DEBUG} null d1 d1", seedling.log)
    }

    @Test
    fun testV() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.v("v")
        assertEquals("${Arbor.VERBOSE} null v", seedling.log)
    }

    @Test
    fun testV1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.v(Exception("v1"), "v1")
        assertEquals("${Arbor.VERBOSE} null v1 v1", seedling.log)
    }

    @Test
    fun testI() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.i("i")
        assertEquals("${Arbor.INFO} null i", seedling.log)
    }

    @Test
    fun testI1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.i(Exception("i1"), "i1")
        assertEquals("${Arbor.INFO} null i1 i1", seedling.log)
    }

    @Test
    fun testW() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.w("w")
        assertEquals("${Arbor.WARNING} null w", seedling.log)
    }

    @Test
    fun testW1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.w(Exception("w1"), "w1")
        assertEquals("${Arbor.WARNING} null w1 w1", seedling.log)
    }

    @Test
    fun testE() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.e("e")
        assertEquals("${Arbor.ERROR} null e", seedling.log)
    }

    @Test
    fun testE1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.e(Exception("e1"), "e1")
        assertEquals("${Arbor.ERROR} null e1 e1", seedling.log)
    }

    @Test
    fun testWtf() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.wtf("wtf")
        assertEquals("${Arbor.WTF} null wtf", seedling.log)
    }

    @Test
    fun testWtf1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.wtf(Exception("wtf1"), "wtf1")
        assertEquals("${Arbor.WTF} null wtf1 wtf1", seedling.log)
    }

}
