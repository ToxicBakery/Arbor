package com.toxicbakery.logging

/**
 * Log a debug message.
 */
fun Branch.d(msg: String, vararg args: Any?) = d(msg, args)

/**
 * Log a verbose message.
 */
fun Branch.v(msg: String, vararg args: Any?) = v(msg, args)

/**
 * Log an info message.
 */
fun Branch.i(msg: String, vararg args: Any?) = i(msg, args)

/**
 * Log a warning message.
 */
fun Branch.w(msg: String, vararg args: Any?) = w(msg, args)

/**
 * Log an error message.
 */
fun Branch.e(msg: String, vararg args: Any?) = e(msg, args)

/**
 * Log a wtf message.
 */
fun Branch.wtf(msg: String, vararg args: Any?) = wtf(msg, args)
