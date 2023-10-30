package com.vass.example.kmmapp.di

import com.vass.example.kmmapp.data.local.datastore.LocalDataSource
import com.vass.example.kmmapp.data.local.datastore.SQLLocalDataSource
import com.vass.example.kmmapp.data.local.db.DatabaseDriverFactory
import com.vass.example.kmmapp.data.local.db.createDatabase
import com.vass.example.kmmapp.data.remote.datastore.QuotesApiRemoteDataSource
import com.vass.example.kmmapp.data.remote.datastore.RemoteDataSource
import com.vass.example.kmmapp.data.repository.QuotesRepository
import com.vass.example.kmmapp.data.repository.QuotesRepositoryImpl
import com.vass.example.kmmapp.domain.usecase.GetAnotherQuoteUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
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


    singleOf(::QuotesApiRemoteDataSource) bind RemoteDataSource::class
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

}