package com.toxicbakery.logging

public class KtorLogSeedling(
    private val ktorLogSeedlingProvider: KtorLogProvider = KtorLogProviderImpl()
) : ISeedling {
    override val tag: String = ""

    override fun log(level: Int, tag: String, msg: String, throwable: Throwable?, args: Array<out Any?>?) {
        require(level >= Arbor.DEBUG && level <= Arbor.WTF)

        val logger = ktorLogSeedlingProvider.getLogger(tag)

        val formattedMessage = when {
            args?.isEmpty() == true -> msg
            else -> msg.format(args)
        }

        when (level) {
            Arbor.DEBUG -> logger.debug(formattedMessage, throwable)
            Arbor.INFO -> logger.info(formattedMessage, throwable)
            Arbor.VERBOSE -> logger.trace(formattedMessage, throwable)
            Arbor.WARNING -> logger.warn(formattedMessage, throwable)
            Arbor.ERROR,
            Arbor.WTF -> logger.error(formattedMessage, throwable)
        }
    }
}
