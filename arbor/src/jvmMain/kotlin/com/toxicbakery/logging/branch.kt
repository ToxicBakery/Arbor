package com.toxicbakery.logging

/**
 * Log a debug message.
 */
public fun Branch.d(msg: String, vararg args: Any?): Unit = d(msg, args)

/**
 * Log a verbose message.
 */
public fun Branch.v(msg: String, vararg args: Any?): Unit = v(msg, args)

/**
 * Log an info message.
 */
public fun Branch.i(msg: String, vararg args: Any?): Unit = i(msg, args)

/**
 * Log a warning message.
 */
public fun Branch.w(msg: String, vararg args: Any?): Unit = w(msg, args)

/**
 * Log an error message.
 */
public fun Branch.e(msg: String, vararg args: Any?): Unit = e(msg, args)

/**
 * Log a wtf message.
 */
public fun Branch.wtf(msg: String, vararg args: Any?): Unit = wtf(msg, args)
