package com.vass.example.kmmapp.data.local.db

import com.squareup.sqldelight.db.SqlDriver

internal expect class DatabaseDriverFactory {
    internal fun createDriver(): SqlDriver
}