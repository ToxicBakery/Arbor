@file:Suppress("TooManyFunctions")

package com.toxicbakery.logging

/**
 * Log a debug message.
 */
fun Branch.d(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.d(throwable, message) }

/**
 * Log an error message.
 */
fun Branch.e(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.e(throwable, message) }

/**
 * Log an info message.
 */
fun Branch.i(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.i(throwable, message) }

/**
 * Log a verbose message.
 */
fun Branch.v(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.v(throwable, message) }

/**
 * Log a warning message.
 */
fun Branch.w(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.w(throwable, message) }

/**
 * Log a wtf message.
 */
fun Branch.wtf(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.wtf(throwable, message) }

/**
 * Log a debug message.
 */
fun Branch.d(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.d(message) }

/**
 * Log an error message.
 */
fun Branch.e(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.e(message) }

/**
 * Log an info message.
 */
fun Branch.i(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.i(message) }

/**
 * Log a verbose message.
 */
fun Branch.v(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.v(message) }

/**
 * Log a warning message.
 */
fun Branch.w(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.w(message) }

/**
 * Log a wtf message.
 */
fun Branch.wtf(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.wtf(message) }
