package com.vass.example.kmmapp.data.local.db

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.java.KoinJavaComponent.getKoin

import sqldelight.vass.db.KMMDatabase

internal actual class DatabaseDriverFactory {
    internal actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(KMMDatabase.Schema, getKoin().get(), "KMMDatabase")
    }
}