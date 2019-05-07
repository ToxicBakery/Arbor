package com.toxicbakery.logging

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class branchTest {

    private lateinit var bufferOut: ByteArrayOutputStream
    private lateinit var bufferErr: ByteArrayOutputStream
    private lateinit var seedling: ISeedling

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
    fun d() {
        Arbor.tag(TAG).d("%s", "arg")
        assertEquals("$TAG: arg\n", bufferOut.toString())
    }

    @Test
    fun v() {
        Arbor.tag(TAG).v("%s", "arg")
        assertEquals("$TAG: arg\n", bufferOut.toString())
    }

    @Test
    fun i() {
        Arbor.tag(TAG).i("%s", "arg")
        assertEquals("$TAG: arg\n", bufferOut.toString())
    }

    @Test
    fun w() {
        Arbor.tag(TAG).w("%s", "arg")
        assertEquals("$TAG: arg\n", bufferOut.toString())
    }

    @Test
    fun e() {
        Arbor.tag(TAG).e("%s", "arg")
        assertEquals("$TAG: arg\n", bufferErr.toString())
    }

    @Test
    fun wtf() {
        Arbor.tag(TAG).wtf("%s", "arg")
        assertEquals("$TAG: arg\n", bufferErr.toString())
    }

    companion object {
        private const val TAG = "tag"
    }

}