package org.example.cinestash.di

import org.koin.core.module.dsl.viewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.cinestash.data.MovieService
import org.example.cinestash.ui.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }
    single { MovieService(get()) }
    viewModel { HomeViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
