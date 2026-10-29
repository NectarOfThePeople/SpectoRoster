package com.nectarofthepeople.spectoroster.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovie(movie: MovieEntity): Long

    @Delete
    fun deleteMovie(movie: MovieEntity): Int

    @Query("SELECT * FROM movies ORDER BY addedAt DESC")
    fun observeAllMovies(): Flow<List<MovieEntity>>
}
