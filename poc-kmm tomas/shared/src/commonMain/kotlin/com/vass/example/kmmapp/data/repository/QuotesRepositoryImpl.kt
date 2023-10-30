package com.vass.example.kmmapp.data.repository

import com.vass.example.kmmapp.data.local.datastore.LocalDataSource
import com.vass.example.kmmapp.data.model.Quote
import com.vass.example.kmmapp.data.remote.datastore.RemoteDataSource

internal class QuotesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : QuotesRepository {
    override suspend fun getQuotes(): List<Quote> {
        return localDataSource.getQuotes()
    }

    override suspend fun insert(): Quote? {
        val newQuote = remoteDataSource.getQuote()
        newQuote?.let {
            localDataSource.insert(it)
        }
        return newQuote
    }

    override suspend fun resetQuotes(): Boolean {
        return localDataSource.resetQuotes()
    }
}