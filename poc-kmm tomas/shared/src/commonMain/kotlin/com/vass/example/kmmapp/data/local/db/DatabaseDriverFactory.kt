package com.vass.example.kmmapp.data.local.db

import com.squareup.sqldelight.db.SqlDriver
import sqldelight.vass.db.KMMDatabase

internal expect class DatabaseDriverFactory constructor() {
    internal fun createDriver(): SqlDriver
}

internal fun createDatabase(driverFactory: DatabaseDriverFactory): KMMDatabase {
    return KMMDatabase(driverFactory.createDriver())
}