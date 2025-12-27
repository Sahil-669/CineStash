package org.example.cinestash.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.cinestash.data.model.Credits
import org.example.cinestash.data.model.Movie
import org.example.cinestash.data.model.MoviesResponse

class MovieService(private val client: HttpClient) {
    private val baseUrl = "https://api.themoviedb.org/3"

    private val apiKey = "your_api_key"

    suspend fun getPopularMovies(page: Int = 1): MoviesResponse {
        return client.get("$baseUrl/movie/popular") {
            parameter("api_key", apiKey)
            parameter("page", page)
        }.body()
    }

    suspend fun searchMovies(query: String): MoviesResponse {
        return client.get("$baseUrl/search/movie") {
            parameter("api_key", apiKey)
            parameter("query", query)
        }.body()
    }

    suspend fun getMovieDetails(movieId: Int): Movie {
        return client.get("$baseUrl/movie/$movieId") {
            parameter("api_key", apiKey)
        }.body()
    }

    suspend fun getMovieCredits(movieId: Int): Credits =
        client.get("$baseUrl/movie/$movieId/credits") {
            parameter("api_key", apiKey)
        }.body()

}

