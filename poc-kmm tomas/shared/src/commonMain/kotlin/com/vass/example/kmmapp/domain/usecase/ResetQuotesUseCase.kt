package com.vass.example.kmmapp.domain.usecase

import com.vass.example.kmmapp.data.repository.QuotesRepository

class ResetQuotesUseCase constructor(private val quotesRepository: QuotesRepository) {
    suspend operator fun invoke() = quotesRepository.resetQuotes()
}