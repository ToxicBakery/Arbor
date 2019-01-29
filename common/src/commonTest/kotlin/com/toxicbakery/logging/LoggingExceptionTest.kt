package com.toxicbakery.logging

import kotlin.test.Test
import kotlin.test.assertEquals

class LoggingExceptionTest {

    @Test
    fun equality() {
        assertEquals(LoggingException("test"), LoggingException("test"))
    }

}
