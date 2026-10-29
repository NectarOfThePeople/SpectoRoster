package com.nectarofthepeople.spectoroster.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val tmdbId: Int,
    val imdbId: String?,
    val title: String,
    val year: Int,
    val shortDescription: String,
    val posterUrl: String,
    val addedAt: Long
)
