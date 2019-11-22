package com.toxicbakery.logging

/**
 * Delegate for printing Android logs. This is useful for injecting a new output such as to a file.
 */
interface LogDelegate {

    /**
     * Write a log with a given level, tag, and message.
     */
    fun writeLog(level: Int, tag: String, msg: String)

    /**
     * Write a log with a given level, tag, message, and a printed throwable exception.
     */
    fun writeLog(level: Int, tag: String, msg: String, throwable: Throwable)

}
