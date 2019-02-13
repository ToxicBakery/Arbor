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
        assertEquals("${Arbor.DEBUG}  d", seedling.log)
    }

    @Test
    fun testD1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.d(Exception("d1"), "d1")
        assertEquals("${Arbor.DEBUG}  d1 d1", seedling.log)
    }

    @Test
    fun testD2() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.d(Exception("d2"))
        assertEquals("${Arbor.DEBUG}   d2", seedling.log)
    }

    @Test
    fun testD3() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.d(Exception("d3"), "msg", "")
        assertEquals("${Arbor.DEBUG}  msg d3", seedling.log)
    }

    @Test
    fun testD4() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.d("msg", "")
        assertEquals("${Arbor.DEBUG}  msg", seedling.log)
    }

    @Test
    fun testV() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.v("v")
        assertEquals("${Arbor.VERBOSE}  v", seedling.log)
    }

    @Test
    fun testV1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.v(Exception("v1"), "v1")
        assertEquals("${Arbor.VERBOSE}  v1 v1", seedling.log)
    }

    @Test
    fun testV2() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.v(Exception("v2"))
        assertEquals("${Arbor.VERBOSE}   v2", seedling.log)
    }

    @Test
    fun testV3() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.v(Exception("v3"), "msg", "")
        assertEquals("${Arbor.VERBOSE}  msg v3", seedling.log)
    }

    @Test
    fun testV4() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.v("msg", "")
        assertEquals("${Arbor.VERBOSE}  msg", seedling.log)
    }

    @Test
    fun testI() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.i("i")
        assertEquals("${Arbor.INFO}  i", seedling.log)
    }

    @Test
    fun testI1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.i(Exception("i1"), "i1")
        assertEquals("${Arbor.INFO}  i1 i1", seedling.log)
    }

    @Test
    fun testI2() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.i(Exception("i2"))
        assertEquals("${Arbor.INFO}   i2", seedling.log)
    }

    @Test
    fun testI3() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.i(Exception("i3"), "msg", "")
        assertEquals("${Arbor.INFO}  msg i3", seedling.log)
    }

    @Test
    fun testI4() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.i("msg", "")
        assertEquals("${Arbor.INFO}  msg", seedling.log)
    }

    @Test
    fun testW() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.w("w")
        assertEquals("${Arbor.WARNING}  w", seedling.log)
    }

    @Test
    fun testW1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.w(Exception("w1"), "w1")
        assertEquals("${Arbor.WARNING}  w1 w1", seedling.log)
    }

    @Test
    fun testW2() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.w(Exception("w2"))
        assertEquals("${Arbor.WARNING}   w2", seedling.log)
    }

    @Test
    fun testW3() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.w(Exception("w3"), "msg", "")
        assertEquals("${Arbor.WARNING}  msg w3", seedling.log)
    }

    @Test
    fun testW4() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.w("msg", "")
        assertEquals("${Arbor.WARNING}  msg", seedling.log)
    }

    @Test
    fun testE() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.e("e")
        assertEquals("${Arbor.ERROR}  e", seedling.log)
    }

    @Test
    fun testE1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.e(Exception("e1"), "e1")
        assertEquals("${Arbor.ERROR}  e1 e1", seedling.log)
    }

    @Test
    fun testE2() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.e(Exception("e2"))
        assertEquals("${Arbor.ERROR}   e2", seedling.log)
    }

    @Test
    fun testE3() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.e(Exception("e3"), "msg", "")
        assertEquals("${Arbor.ERROR}  msg e3", seedling.log)
    }

    @Test
    fun testE4() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.e("msg", "")
        assertEquals("${Arbor.ERROR}  msg", seedling.log)
    }

    @Test
    fun testWtf() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.wtf("wtf")
        assertEquals("${Arbor.WTF}  wtf", seedling.log)
    }

    @Test
    fun testWtf1() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.wtf(Exception("wtf1"), "wtf1")
        assertEquals("${Arbor.WTF}  wtf1 wtf1", seedling.log)
    }

    @Test
    fun testWtf2() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.wtf(Exception("wtf2"))
        assertEquals("${Arbor.WTF}   wtf2", seedling.log)
    }

    @Test
    fun testWtf3() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.wtf(Exception("wtf3"), "msg", "")
        assertEquals("${Arbor.WTF}  msg wtf3", seedling.log)
    }

    @Test
    fun testWtf4() {
        val seedling = TestSeedling()
        Arbor.sow(seedling)
        Arbor.wtf("msg", "")
        assertEquals("${Arbor.WTF}  msg", seedling.log)
    }

}
