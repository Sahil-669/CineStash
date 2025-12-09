package org.example.cinestash.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.cinestash.data.database.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual val databaseModule: Module = module {
    single<AppDatabase> {
        val dbFile = File(System.getProperty("user.home"), "cinestash.db")
        Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
            factory = { AppDatabase::class.instantiateImpl()}
        ).setDriver(BundledSQLiteDriver())
            .build()
    }
}