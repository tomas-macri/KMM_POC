package com.vass.example.kmmapp.di

import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class Koin : KoinComponent {
    fun doInitKoin(){
        startKoin {
            modules(sharedModule)
        }
    }
}