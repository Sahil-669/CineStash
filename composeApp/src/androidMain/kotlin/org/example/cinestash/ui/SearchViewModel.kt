package org.example.cinestash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.cinestash.data.MovieService

class SearchViewModel (
    private val movieService: MovieService
): ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    fun onQueryChanged(newQuery: String) {
        _state.update { it.copy(query = newQuery) }
    }

    fun onSearch() {
        val currentQuery = _state.value.query
        if (currentQuery.isBlank()) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val response = movieService.searchMovies(currentQuery)
                _state.update { it.copy(
                    isLoading = false,
                    movies = response.results
                ) }
            } catch (e: Exception) {
                _state.update { it.copy(
                    isLoading = false,
                    errorMessage = "Search Failed: ${e.message}"
                ) }
            }
        }
    }
}