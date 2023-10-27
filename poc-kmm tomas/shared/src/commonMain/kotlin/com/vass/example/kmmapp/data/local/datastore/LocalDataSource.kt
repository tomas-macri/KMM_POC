package com.vass.example.kmmapp.data.local.datastore

import com.vass.example.kmmapp.data.model.Quote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import vass.db.KMMDatabaseQueries

internal interface LocalDataSource {
    suspend fun getQuotes(): List<Quote>

    suspend fun insert(newQuote: Quote)

    suspend fun resetQuotes()
}

internal class SQLLocalDataSource(
    private val queriesProvider: KMMDatabaseQueries,
    private val dispatcher: CoroutineDispatcher,
): LocalDataSource{
    override suspend fun getQuotes(): List<Quote> {
        return withContext(dispatcher){
            queriesProvider.getQuotes().executeAsList().map {
                Quote(it.id.toInt(), it.message, it.author)
            }
        }
    }

    override suspend fun insert(newQuote: Quote) {
        withContext(dispatcher){
            queriesProvider.insertQuote(null, newQuote.quote, newQuote.author)
        }
    }

    override suspend fun resetQuotes() {
        withContext(dispatcher){
            queriesProvider.resetQuotes()
        }
    }

}