package org.example.cinestash.ui

import org.example.cinestash.data.model.Movie


data class SearchState (
    val query: String = "",
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)