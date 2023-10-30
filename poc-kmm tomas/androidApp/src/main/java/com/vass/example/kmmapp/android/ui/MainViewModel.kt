package com.vass.example.kmmapp.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vass.example.kmmapp.domain.usecase.GetAnotherQuoteUseCase
import kotlinx.coroutines.launch

class MainViewModel constructor(
    val getAnotherQuoteUseCase: GetAnotherQuoteUseCase
): ViewModel() {


    private val _mainUiState = MutableLiveData(MainUiState())
    val mainUiState: LiveData<MainUiState> = _mainUiState

    fun getAnotherQuote(){
        setLoading()
        viewModelScope.launch {
            getAnotherQuoteUseCase().also {
                _mainUiState.value = _mainUiState.value?.copy(loading = false)
            }
        }
    }

    private fun setLoading() {
        _mainUiState.value = _mainUiState.value?.copy(loading = true, error = null)
    }
}