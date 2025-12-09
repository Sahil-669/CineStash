package org.example.cinestash.ui

import org.example.cinestash.data.model.CastMember
import org.example.cinestash.data.model.Movie

data class DetailState (
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val errorMessage: String? = null,
    val isFavourite: Boolean = false,
    val cast: List<CastMember> = emptyList()
)