@file:Suppress("TooManyFunctions")

package com.toxicbakery.logging

/**
 * Log a debug message.
 */
fun Arbor.d(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.d(throwable, message) }

/**
 * Log an error message.
 */
fun Arbor.e(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.e(throwable, message) }

/**
 * Log an info message.
 */
fun Arbor.i(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.i(throwable, message) }

/**
 * Log a verbose message.
 */
fun Arbor.v(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.v(throwable, message) }

/**
 * Log a warning message.
 */
fun Arbor.w(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.w(throwable, message) }

/**
 * Log a wtf message.
 */
fun Arbor.wtf(
    throwable: Throwable,
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.wtf(throwable, message) }

/**
 * Log a debug message.
 */
fun Arbor.d(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.d(message) }

/**
 * Log an error message.
 */
fun Arbor.e(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.e(message) }

/**
 * Log a info message.
 */
fun Arbor.i(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.i(message) }

/**
 * Log a verbose message.
 */
fun Arbor.v(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.v(message) }

/**
 * Log a warning message.
 */
fun Arbor.w(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.w(message) }

/**
 * Log a wtf message.
 */
fun Arbor.wtf(
    message: String,
    vararg args: Any
) = message.format(args)
    .let { Arbor.wtf(message) }
