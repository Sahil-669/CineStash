package org.example.cinestash.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.cinestash.data.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual val databaseModule: Module = module {
    single <AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/cinestash.db"
        Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
            factory = { AppDatabase::class.instantiateImpl()}
        )
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}