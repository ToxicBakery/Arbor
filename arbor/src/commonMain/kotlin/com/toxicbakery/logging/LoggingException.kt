package com.toxicbakery.logging

/**
 * A generic exception thrown when logging can not be performed.
 */
public data class LoggingException(private val msg: String) : Exception(msg)
