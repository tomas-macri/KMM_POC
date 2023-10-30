package com.vass.example.kmmapp.data.remote.datastore

import com.vass.example.kmmapp.data.model.Quote
import com.vass.example.kmmapp.data.remote.api.model.QuoteDTO
import com.vass.example.kmmapp.data.remote.api.model.toQuote
import com.vass.example.kmmappp.BuildKonfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal interface RemoteDataSource {
    suspend fun getQuote(): Quote?

    suspend fun getQuotes(amount: Int = 1): List<Quote>
}

internal class QuotesApiRemoteDataSource(
    private val ktorClient: HttpClient
): RemoteDataSource {
    override suspend fun getQuote(): Quote? {
        val response: List<QuoteDTO> = ktorClient.get("${BuildKonfig.apiUrl}quotes").body()
        return response.firstOrNull()?.toQuote()
    }

    override suspend fun getQuotes(amount: Int): List<Quote> {
        val response: List<QuoteDTO> = ktorClient.get("${BuildKonfig.apiUrl}quotes/$amount").body()
        return response.map { it.toQuote() }
    }
}
