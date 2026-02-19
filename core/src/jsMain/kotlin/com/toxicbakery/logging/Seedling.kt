package com.toxicbakery.logging

import com.toxicbakery.logging.Arbor.DEBUG
import com.toxicbakery.logging.Arbor.ERROR
import com.toxicbakery.logging.Arbor.INFO
import com.toxicbakery.logging.Arbor.VERBOSE
import com.toxicbakery.logging.Arbor.WARNING
import com.toxicbakery.logging.Arbor.WTF

/**
 * Basic logger that prints messages to the JavaScript Console.
 */
public class Seedling : ISeedling {

    override val tag: String
        get() = ""

    override fun log(
        level: Int,
        tag: String,
        msg: String,
        throwable: Throwable?,
        args: Array<out Any?>?
    ) {
        require(level in DEBUG..WTF)
        val message = "$tag$msg"
        if (message.isEmpty() && throwable == null) return
        if (throwable == null) log(level, message)
        else logWithThrowable(level, throwable, message)
    }

    private fun log(
        level: Int,
        message: String
    ) {
        when (level) {
            DEBUG -> console.log(message)
            INFO,
            VERBOSE -> console.info(message)
            WARNING -> console.warn(message)
            ERROR,
            WTF -> console.error(message)
        }
    }

    private fun logWithThrowable(
        level: Int,
        throwable: Throwable,
        message: String
    ) {
        when (level) {
            DEBUG -> console.log(throwable, message)
            INFO,
            VERBOSE -> console.info(throwable, message)
            WARNING -> console.warn(throwable, message)
            ERROR,
            WTF -> console.error(throwable, message)
        }
    }
}
