package com.toxicbakery.logging

import io.ktor.util.logging.KtorSimpleLogger
import io.ktor.util.logging.Logger

internal class KtorLogProviderImpl : KtorLogProvider {
    private val loggerCache = mutableMapOf<String, Logger>()

    override fun getLogger(tag: String): Logger =
        loggerCache.getOrPut(tag) { KtorSimpleLogger(tag) }
}
