package org.example.cinestash.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMovieDao {
    @Query("SELECT * FROM FavouriteMovie")
    fun getAllFavourites(): Flow<List<FavouriteMovie>>

    @Query("SELECT * FROM FavouriteMovie WHERE id = :id")
    suspend fun getFavouriteById(id: Int): FavouriteMovie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(movie: FavouriteMovie)

    @Delete
    suspend fun deleteFavourite(movie: FavouriteMovie)
}