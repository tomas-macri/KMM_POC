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

    private val _inserted = MutableLiveData<Boolean>()
    val inserted: LiveData<Boolean> = _inserted
    fun getAnotherQuote(){
        viewModelScope.launch {
            getAnotherQuoteUseCase().also {
                _inserted.value = _inserted.value?.not()?:true
            }
        }
    }

}