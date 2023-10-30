package com.vass.example.kmmapp.data.remote.datastore

import com.vass.example.kmmapp.data.model.Quote
import com.vass.example.kmmapp.data.remote.api.model.QuoteDTO
import com.vass.example.kmmapp.data.remote.api.model.toQuote
import com.vass.example.kmmapp.utils.logger.KMMLogger
import com.vass.example.kmmappp.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal interface RemoteDataSource {
    suspend fun getQuote(): Quote?

    suspend fun getQuotes(amount: Int = 1): List<Quote>
}

internal class QuotesApiRemoteDataSource(
    private val ktorClient: HttpClient,
    private val dispatcher: CoroutineDispatcher,
    private val logger: KMMLogger
) : RemoteDataSource {
    companion object {
        private const val TAG = "RemoteDataSource"
    }

    override suspend fun getQuote(): Quote? {
        return withContext(dispatcher) {
            val apiCallUrl = "${BuildKonfig.apiUrl}quotes"
            logger.debug(TAG, "[getQuote] --> $apiCallUrl")
            val response = ktorClient.get(apiCallUrl)
            with(response) {
                val newQuoteAsList: List<QuoteDTO> = body()
                logger.debug(TAG, "[getQuote] <-- $status")
                logger.debug(TAG, "[getQuote] content <-- $newQuoteAsList ")
                newQuoteAsList.firstOrNull()?.toQuote()
            }
        }
    }

    override suspend fun getQuotes(amount: Int): List<Quote> {
        return withContext(dispatcher) {
            val response = ktorClient.get("${BuildKonfig.apiUrl}quotes/$amount")
            with(response) {
                val newQuotes: List<QuoteDTO> = body()
                logger.debug(TAG, "[getQuotes] <-- $status")
                logger.debug(TAG, "[getQuotes] content <-- $newQuotes")
                newQuotes.map { it.toQuote() }
            }
        }
    }

}
