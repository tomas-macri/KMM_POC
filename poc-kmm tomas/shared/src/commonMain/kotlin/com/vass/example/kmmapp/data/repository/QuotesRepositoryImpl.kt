package com.vass.example.kmmapp.data.repository

import com.vass.example.kmmapp.data.local.datastore.LocalDataSource
import com.vass.example.kmmapp.data.model.Quote

internal class QuotesRepositoryImpl(
    private val localDataSource: LocalDataSource
): QuotesRepository {
    override suspend fun getQuotes(): List<Quote> {
        return localDataSource.getQuotes()
    }

    override suspend fun insert(newQuote: Quote) {
        return localDataSource.insert(newQuote)
    }

    override suspend fun resetQuotes() {
        return localDataSource.resetQuotes()
    }
}