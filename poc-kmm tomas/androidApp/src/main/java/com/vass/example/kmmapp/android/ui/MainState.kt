package com.vass.example.kmmapp.android.ui

import com.vass.example.kmmapp.data.model.Quote

data class MainUiState(val quotes:List<Quote> = emptyList(), val loading: Boolean = false, val error: String? = null)