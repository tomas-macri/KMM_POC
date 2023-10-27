package com.vass.example.kmmapp.di

import com.vass.example.kmmapp.data.local.datastore.LocalDataSource
import com.vass.example.kmmapp.data.local.datastore.SQLLocalDataSource
import com.vass.example.kmmapp.data.local.db.DatabaseDriverFactory
import com.vass.example.kmmapp.data.local.db.createDatabase
import com.vass.example.kmmapp.data.repository.QuotesRepository
import com.vass.example.kmmapp.data.repository.QuotesRepositoryImpl
import com.vass.example.kmmapp.domain.usecase.GetAnotherQuoteUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    //Cosas que afectan al módulo shared
    factoryOf(::GetAnotherQuoteUseCase)
    singleOf(::QuotesRepositoryImpl) bind QuotesRepository::class
    single { createDatabase(get()).kMMDatabaseQueries }
    factory { Dispatchers.Default }
    singleOf(::DatabaseDriverFactory)
    singleOf(::SQLLocalDataSource) bind LocalDataSource::class

}