package com.vass.example.kmmapp.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vass.example.kmmapp.domain.usecase.GetAllQuotesUseCase
import com.vass.example.kmmapp.domain.usecase.GetAnotherQuoteUseCase
import com.vass.example.kmmapp.domain.usecase.ResetQuotesUseCase
import kotlinx.coroutines.launch

class MainViewModel constructor(
    val getAllQuotesUseCase: GetAllQuotesUseCase,
    val getAnotherQuoteUseCase: GetAnotherQuoteUseCase,
    val resetQuotesUseCase: ResetQuotesUseCase
) : ViewModel() {


    private val _mainUiState = MutableLiveData(MainUiState())
    val mainUiState: LiveData<MainUiState> = _mainUiState

    fun getAnotherQuote() {
        setLoading()
        viewModelScope.launch {
            getAnotherQuoteUseCase().also { newQuote ->
                newQuote?.let {
                    val updatedQuotes = _mainUiState.value?.quotes?.plus(it) ?: listOf(it)
                    _mainUiState.value = _mainUiState.value?.copy(quotes = updatedQuotes, loading = false)
                }
                _mainUiState.value = _mainUiState.value?.copy(loading = false, error = "The quote couldn't be added")
            }
        }
    }

    fun getAllQuotes() {
        setLoading()
        viewModelScope.launch {
            _mainUiState.value = _mainUiState.value?.copy(quotes = getAllQuotesUseCase(), loading = false)
        }
    }

    fun resetQuotes() {
        setLoading()
        viewModelScope.launch {
            if (resetQuotesUseCase()) {
                _mainUiState.value = _mainUiState.value?.copy(quotes = listOf(), loading = false)
            } else {
                _mainUiState.value = _mainUiState.value?.copy(loading = false, error = "There was an error while deleting the quotes")
            }
        }
    }

    private fun setLoading() {
        _mainUiState.value = _mainUiState.value?.copy(loading = true, error = null)
    }

}