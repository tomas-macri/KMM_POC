package com.vass.example.kmmapp.data.repository


import com.vass.example.kmmapp.data.model.Quote

interface QuotesRepository{
    suspend fun getQuotes(): List<Quote>

    suspend fun insert()

    suspend fun resetQuotes()
}