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
    fun v() {
        branch.v(Exception())
        assertEquals("${Arbor.VERBOSE} tag ", seedling.log)
    }

    @Test
    fun i() {
        branch.i(Exception())
        assertEquals("${Arbor.INFO} tag ", seedling.log)
    }

    @Test
    fun w() {
        branch.w(Exception())
        assertEquals("${Arbor.WARNING} tag ", seedling.log)
    }

    @Test
    fun e() {
        branch.e(Exception())
        assertEquals("${Arbor.ERROR} tag ", seedling.log)
    }

    @Test
    fun wtf() {
        branch.wtf(Exception())
        assertEquals("${Arbor.WTF} tag ", seedling.log)
    }

}
