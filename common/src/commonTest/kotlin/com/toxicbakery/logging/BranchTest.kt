package com.toxicbakery.logging

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BranchTest {

    private lateinit var seedling: TestSeedling
    private lateinit var branch: Branch

    @BeforeTest
    fun setup() {
        seedling = TestSeedling("tag")
        branch = Branch(seedlings = setOf(seedling))
    }

    @Test
    fun d() {
        branch.d(Exception())
        assertEquals("${Arbor.DEBUG} tag ", seedling.log)
    }

    @Test
    fun d1() {
        branch.d("msg")
        assertEquals("${Arbor.DEBUG} tag msg", seedling.log)
    }

    @Test
    fun d2() {
        branch.d("msg", "")
        assertEquals("${Arbor.DEBUG} tag msg", seedling.log)
    }

    @Test
    fun d3() {
        branch.d(Exception(), "msg", "")
        assertEquals("${Arbor.DEBUG} tag msg", seedling.log)
    }

    @Test
    fun v() {
        branch.v(Exception())
        assertEquals("${Arbor.VERBOSE} tag ", seedling.log)
    }

    @Test
    fun v1() {
        branch.v("msg")
        assertEquals("${Arbor.VERBOSE} tag msg", seedling.log)
    }

    @Test
    fun v2() {
        branch.v("msg", "")
        assertEquals("${Arbor.VERBOSE} tag msg", seedling.log)
    }

    @Test
    fun v3() {
        branch.v(Exception(), "msg", "")
        assertEquals("${Arbor.VERBOSE} tag msg", seedling.log)
    }

    @Test
    fun i() {
        branch.i(Exception())
        assertEquals("${Arbor.INFO} tag ", seedling.log)
    }

    @Test
    fun i1() {
        branch.i("msg")
        assertEquals("${Arbor.INFO} tag msg", seedling.log)
    }

    @Test
    fun i2() {
        branch.i("msg", "")
        assertEquals("${Arbor.INFO} tag msg", seedling.log)
    }

    @Test
    fun i3() {
        branch.i(Exception(), "msg", "")
        assertEquals("${Arbor.INFO} tag msg", seedling.log)
    }

    @Test
    fun w() {
        branch.w(Exception())
        assertEquals("${Arbor.WARNING} tag ", seedling.log)
    }

    @Test
    fun w1() {
        branch.w("msg")
        assertEquals("${Arbor.WARNING} tag msg", seedling.log)
    }

    @Test
    fun w2() {
        branch.w("msg", "")
        assertEquals("${Arbor.WARNING} tag msg", seedling.log)
    }

    @Test
    fun w3() {
        branch.w(Exception(), "msg", "")
        assertEquals("${Arbor.WARNING} tag msg", seedling.log)
    }

    @Test
    fun e() {
        branch.e(Exception())
        assertEquals("${Arbor.ERROR} tag ", seedling.log)
    }

    @Test
    fun e1() {
        branch.e("msg")
        assertEquals("${Arbor.ERROR} tag msg", seedling.log)
    }

    @Test
    fun e2() {
        branch.e("msg", "")
        assertEquals("${Arbor.ERROR} tag msg", seedling.log)
    }

    @Test
    fun e3() {
        branch.e(Exception(), "msg", "")
        assertEquals("${Arbor.ERROR} tag msg", seedling.log)
    }

    @Test
    fun wtf() {
        branch.wtf(Exception())
        assertEquals("${Arbor.WTF} tag ", seedling.log)
    }

    @Test
    fun wtf1() {
        branch.wtf("msg")
        assertEquals("${Arbor.WTF} tag msg", seedling.log)
    }

    @Test
    fun wtf2() {
        branch.wtf("msg", "")
        assertEquals("${Arbor.WTF} tag msg", seedling.log)
    }

    @Test
    fun wtf3() {
        branch.wtf(Exception(), "msg", "")
        assertEquals("${Arbor.WTF} tag msg", seedling.log)
    }

}
