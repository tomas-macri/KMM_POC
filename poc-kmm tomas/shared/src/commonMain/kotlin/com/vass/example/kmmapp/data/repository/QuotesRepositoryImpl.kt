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

    override suspend fun insert() {
        val newQuote = remoteDataSource.getQuote()
        newQuote?.let {
            localDataSource.insert(it)
        }
    }

    override suspend fun resetQuotes() {
        return localDataSource.resetQuotes()
    }
}