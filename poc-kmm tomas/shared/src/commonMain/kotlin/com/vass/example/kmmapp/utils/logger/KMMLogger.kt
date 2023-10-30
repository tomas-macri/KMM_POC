package com.vass.example.kmmapp.utils.logger

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity

internal interface KMMLogger {
    fun enable()
    fun disable()
    fun verbose(tag: String, message: String)
    fun debug(tag: String, message: String)
    fun info(tag: String, message: String)
    fun error(tag: String, message: String)
}

class KMMLoggerImpl(enable: Boolean = true) : KMMLogger {
    companion object {
        private const val TAG = "KMMLogger"
    }

    private lateinit var logger: Logger

    init {
        if (enable) {
            enable()
        } else {
            disable()
        }
    }

    override fun enable() {
        logger = Logger.withTag(TAG).apply { Logger.setMinSeverity(Severity.Verbose) }
    }

    override fun disable() {
        logger = Logger.withTag(TAG).apply { Logger.setMinSeverity(Severity.Warn) }
    }

    override fun verbose(tag: String, message: String) {
        logger.v("$tag : $message")
    }

    override fun debug(tag: String, message: String) {
        logger.d("$tag : $message")
    }

    override fun info(tag: String, message: String) {
        logger.i("$tag : $message")
    }

    override fun error(tag: String, message: String) {
        logger.e("$tag : $message")
    }
}