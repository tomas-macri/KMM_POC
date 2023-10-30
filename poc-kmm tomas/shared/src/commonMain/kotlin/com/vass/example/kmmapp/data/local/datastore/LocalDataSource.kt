package com.vass.example.kmmapp.data.local.datastore

import com.vass.example.kmmapp.data.model.Quote
import com.vass.example.kmmapp.utils.logger.KMMLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import vass.db.KMMDatabaseQueries

internal interface LocalDataSource {
    suspend fun getQuotes(): List<Quote>

    suspend fun insert(newQuote: Quote)

    suspend fun resetQuotes(): Boolean
}

internal class SQLLocalDataSource(
    private val queriesProvider: KMMDatabaseQueries,
    private val dispatcher: CoroutineDispatcher,
    private val logger: KMMLogger
) : LocalDataSource {
    companion object {
        private const val TAG = "LocalDataStore"
    }

    override suspend fun getQuotes(): List<Quote> {
        return withContext(dispatcher) {
            queriesProvider.getQuotes().executeAsList().map {
                Quote(it.id.toInt(), it.message, it.author)
            }.also {
                logger.debug(TAG, "[getQuotes] $it")
            }
        }
    }

    override suspend fun insert(newQuote: Quote) {
        withContext(dispatcher) {
            queriesProvider.insertQuote(null, newQuote.quote, newQuote.author).also {
                logger.debug(TAG, "[insert] $newQuote")
            }
        }
    }

    override suspend fun resetQuotes(): Boolean {
        return withContext(dispatcher) {
            queriesProvider.resetQuotes()
            logger.debug(TAG, "[resetQuotes]")
            return@withContext true
        }
    }

}