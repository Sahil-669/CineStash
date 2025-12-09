package org.example.cinestash.ui

import org.example.cinestash.data.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)