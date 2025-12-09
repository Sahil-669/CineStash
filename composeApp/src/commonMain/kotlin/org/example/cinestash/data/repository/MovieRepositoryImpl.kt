package org.example.cinestash.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.cinestash.data.MovieService
import org.example.cinestash.data.database.FavouriteMovie
import org.example.cinestash.data.database.FavouriteMovieDao
import org.example.cinestash.data.model.CastMember
import org.example.cinestash.data.model.Movie

class MovieRepositoryImpl (
    private val api: MovieService,
    private val dao: FavouriteMovieDao
): MovieRepository {
    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return api.getPopularMovies(page).results
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return api.searchMovies(query).results
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        return api.getMovieDetails(movieId)
    }

    override suspend fun getMovieCast(movieId: Int): List<CastMember> {
        return api.getMovieCredits(movieId).cast
    }

    override fun getAllFavorites(): Flow<List<FavouriteMovie>> {
        return dao.getAllFavourites()
    }

    override suspend fun isMovieFavorite(movieId: Int): Boolean {
        return dao.getFavouriteById(movieId) != null
    }

    override suspend fun toggleFavorite(movie: Movie, isCurrentlyFavorite: Boolean) {
        if (isCurrentlyFavorite) {
            val movieToDelete = FavouriteMovie(
                id = movie.id, title = "", posterPath = "", releaseDate = "", rating = 0.0, overview = ""
            )
            dao.deleteFavourite(movieToDelete)
        } else {
            val favorite = FavouriteMovie(
                id = movie.id,
                title = movie.title,
                posterPath = movie.posterPath,
                releaseDate = movie.releaseDate,
                rating = movie.rating,
                overview = movie.overview
            )
            dao.insertFavourite(favorite)
        }
    }

    override suspend fun getFavouriteById(movieId: Int): FavouriteMovie? {
        return dao.getFavouriteById(movieId)
    }

    override suspend fun deleteFavourite(movie: FavouriteMovie) {
        return dao.deleteFavourite(movie)
    }

    override suspend fun insertFavourite(movie: FavouriteMovie) {
        return dao.insertFavourite(movie)
    }
}