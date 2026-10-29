package com.nectarofthepeople.spectoroster.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class TmdbSearchResponse(
    val results: List<TmdbSearchItem> = emptyList()
)

@Serializable
data class TmdbSearchItem(
    val id: Int,
    val title: String,
    val release_date: String? = null,
    val poster_path: String? = null,
    val overview: String? = null
)

@Serializable
data class TmdbMovieDetails(
    val id: Int,
    val imdb_id: String? = null,
    val title: String,
    val release_date: String? = null,
    val tagline: String? = null,
    val overview: String? = null,
    val poster_path: String? = null
)

@Serializable
data class TmdbExternalIds(
    val imdb_id: String? = null
)
