package com.vass.example.kmmapp.data.remote.api.model

import com.vass.example.kmmapp.data.model.Quote
import kotlinx.serialization.Serializable

@Serializable
data class QuoteDTO(
    val quote: String, val author: String
)

fun QuoteDTO.toQuote() = Quote(0, quote, author)