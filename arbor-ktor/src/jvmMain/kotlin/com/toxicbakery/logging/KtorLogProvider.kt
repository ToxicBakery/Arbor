package com.toxicbakery.logging

import io.ktor.util.logging.Logger

public interface KtorLogProvider {
    public fun getLogger(tag: String): Logger
}
