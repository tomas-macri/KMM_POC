package com.vass.example.kmmapp.android

import android.app.Application
import com.vass.example.kmmapp.android.ui.MainViewModel
import com.vass.example.kmmapp.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KMMApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KMMApplication)
            modules(module, sharedModule)
        }
    }
}

val module = module {
    //Cosas que solo afectan a Android
    viewModelOf(::MainViewModel)
}