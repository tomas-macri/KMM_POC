package com.vass.example.kmmapp.domain.usecase

import com.vass.example.kmmapp.data.model.Quote
import com.vass.example.kmmapp.data.repository.QuotesRepository

class GetAnotherQuoteUseCase constructor(private val quotesRepository: QuotesRepository) {
     suspend operator fun invoke() = quotesRepository.insert(Quote(0,"This is a quote", "Author" ))
}