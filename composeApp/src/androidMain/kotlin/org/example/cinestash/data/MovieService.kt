package org.example.cinestash.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.cinestash.data.model.MoviesResponse

class MovieService(private val client: HttpClient) {
    private val baseUrl = "https://api.themoviedb.org/3"

    private val apiKey = "f9cd5644d05e9a41bd627842861390c9"

    suspend fun getPopularMovies(): MoviesResponse {
        return client.get("$baseUrl/movie/popular") {
            parameter("api_key", apiKey)
        }.body()
    }

    suspend fun searchMovies(query: String): MoviesResponse {
        return client.get("$baseUrl/search/movie") {
            parameter("api_key", apiKey)
            parameter("query", query)
        }.body()
    }
}

