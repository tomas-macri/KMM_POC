package com.vass.example.kmmapp.data.local.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import sqldelight.vass.db.KMMDatabase

internal actual class DatabaseDriverFactory {
    internal actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(KMMDatabase.Schema, "KMMDatabase")
    }
}