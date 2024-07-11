package com.vass.example.kmmapp.domain.usecase

import com.vass.example.kmmapp.data.repository.QuotesRepository

class GetAllQuotesUseCase constructor(private val quotesRepository: QuotesRepository) {
    suspend operator fun invoke() = quotesRepository.getQuotes()
}