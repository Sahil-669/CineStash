package org.example.cinestash.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.cinestash.data.database.FavouriteMovie
import org.example.cinestash.data.model.CastMember
import org.example.cinestash.data.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovieDetails(movieId: Int): Movie
    suspend fun getMovieCast(movieId: Int): List<CastMember>

    fun getAllFavorites(): Flow<List<FavouriteMovie>>
    suspend fun isMovieFavorite(movieId: Int): Boolean
    suspend fun toggleFavorite(movie: Movie, isCurrentlyFavorite: Boolean)
    suspend fun getFavouriteById(movieId: Int): FavouriteMovie?
    suspend fun deleteFavourite(movie: FavouriteMovie)
    suspend fun insertFavourite(movie: FavouriteMovie)
}