package org.example.cinestash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.cinestash.data.repository.MovieRepository

class DetailViewModel (
    private val movieId: Int,
    private val repository: MovieRepository
): ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()
    private val _snackbarEvent = Channel<String>()
    val snackbarEvent = _snackbarEvent.receiveAsFlow()

    init {
        checkIfFavourite()
        getMovieDetails()
    }

    private fun checkIfFavourite() {
        viewModelScope.launch {
            val favourite = repository.getFavouriteById(movieId)
            _state.update { it.copy(isFavourite = favourite != null) }
        }
    }

    fun toggleFavourite() {
        val currentMovie = state.value.movie ?: return

        viewModelScope.launch {
            repository.toggleFavorite(currentMovie, state.value.isFavourite)
            if (state.value.isFavourite) {
                _state.update { it.copy(isFavourite = false) }
                _snackbarEvent.send("Removed from Stash!")
            } else {
                _state.update { it.copy(isFavourite = true) }
                _snackbarEvent.send("Added to Stash")
            }
        }

    }
    fun getMovieDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val movie = repository.getMovieDetails(movieId)
                val credits = repository.getMovieCast(movieId)
                _state.update {
                    it.copy(
                    isLoading = false,
                    movie = movie,
                        cast = credits.take(10)
                )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.update { it.copy(
                    isLoading = false,
                    errorMessage = "Failed to load movie details: ${e.message}"
                ) }
            }
        }
    }
}