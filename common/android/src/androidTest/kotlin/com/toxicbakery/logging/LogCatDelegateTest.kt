package com.toxicbakery.logging

import org.junit.Test

class LogCatDelegateTest {

    @Test
    fun writeLog() {
        LogCatDelegate.writeLog(Arbor.DEBUG, "tag", "msg")
        LogCatDelegate.writeLog(Arbor.ERROR, "tag", "msg")
        LogCatDelegate.writeLog(Arbor.INFO, "tag", "msg")
        LogCatDelegate.writeLog(Arbor.VERBOSE, "tag", "msg")
        LogCatDelegate.writeLog(Arbor.WARNING, "tag", "msg")
        LogCatDelegate.writeLog(Arbor.WTF, "tag", "msg")

        LogCatDelegate.writeLog(Arbor.DEBUG, "tag", "msg", Exception())
        LogCatDelegate.writeLog(Arbor.ERROR, "tag", "msg", Exception())
        LogCatDelegate.writeLog(Arbor.INFO, "tag", "msg", Exception())
        LogCatDelegate.writeLog(Arbor.VERBOSE, "tag", "msg", Exception())
        LogCatDelegate.writeLog(Arbor.WARNING, "tag", "msg", Exception())
        LogCatDelegate.writeLog(Arbor.WTF, "tag", "msg", Exception())
    }

    @Test
    fun invalidLevel_withoutException() {
        LogCatDelegate.writeLog(0, "tag", "msg")
    }

    @Test
    fun invalidLevel_withException() {
        LogCatDelegate.writeLog(0, "tag", "msg", Exception())
    }

}
