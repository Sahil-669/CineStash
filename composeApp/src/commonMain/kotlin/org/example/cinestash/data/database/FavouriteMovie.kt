package org.example.cinestash.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteMovie(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String?,
    val rating: Double,
    val overview: String
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
}