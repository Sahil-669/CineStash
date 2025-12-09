package org.example.cinestash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.cinestash.data.repository.MovieRepository

class HomeViewModel (
    private val repository: MovieRepository
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state
    private var currentPage = 1
    private var canLoadMore = true

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        if (_state.value.isLoading || !canLoadMore) return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val response = repository.getPopularMovies(page = currentPage)
                if (response.isEmpty()) {
                    canLoadMore = false
                    _state.update { it.copy(isLoading = false) }
                } else {
                    val currentMovies = _state.value.movies
                    val newMovies = currentMovies + response
                    currentPage ++

                    _state.update {
                        it.copy(
                            isLoading = false,
                            movies = newMovies
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Failed to load movies: ${e.message}"
                    )
                }
            }
        }
    }
}