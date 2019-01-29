package com.toxicbakery.logging

data class LoggingException(private val msg: String) : Exception(msg)
