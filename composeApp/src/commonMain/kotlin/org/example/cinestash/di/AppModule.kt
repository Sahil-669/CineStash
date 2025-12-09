package org.example.cinestash.di

import org.koin.core.module.dsl.viewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.cinestash.data.MovieService
import org.example.cinestash.data.database.AppDatabase
import org.example.cinestash.data.repository.MovieRepository
import org.example.cinestash.data.repository.MovieRepositoryImpl
import org.example.cinestash.ui.DetailViewModel
import org.example.cinestash.ui.HomeViewModel
import org.example.cinestash.ui.SearchViewModel
import org.example.cinestash.ui.StashViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect val databaseModule: Module
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
    single { get<AppDatabase>().favouriteMovieDao() }
    single { MovieService(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { StashViewModel(get()) }
    viewModel { (movieId: Int) -> DetailViewModel(movieId,get()) }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, databaseModule)
    }
}
