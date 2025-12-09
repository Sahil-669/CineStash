package org.example.cinestash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.example.cinestash.data.database.FavouriteMovie
import org.example.cinestash.data.database.FavouriteMovieDao

class StashViewModel (
    dao: FavouriteMovieDao
) : ViewModel() {

    val favourites: StateFlow<List<FavouriteMovie>> = dao.getAllFavourites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}