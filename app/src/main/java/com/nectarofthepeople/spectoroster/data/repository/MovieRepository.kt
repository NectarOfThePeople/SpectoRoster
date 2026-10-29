package com.nectarofthepeople.spectoroster.data.repository

import com.nectarofthepeople.spectoroster.data.local.MovieDao
import com.nectarofthepeople.spectoroster.data.local.MovieEntity
import com.nectarofthepeople.spectoroster.data.remote.TmdbApiService
import com.nectarofthepeople.spectoroster.data.remote.TmdbSearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class MovieRepository(
    private val apiService: TmdbApiService,
    private val movieDao: MovieDao
) {
    val allMovies: Flow<List<MovieEntity>> = movieDao.observeAllMovies()

    suspend fun searchMovies(query: String, apiKey: String): List<TmdbSearchItem> {
        return apiService.searchMovies(query, apiKey).results
    }

    suspend fun addMovie(tmdbId: Int, apiKey: String) {
        val details = apiService.getMovieDetails(tmdbId, apiKey)
        var imdbId = details.imdb_id
        if (imdbId.isNullOrBlank()) {
            try {
                val externalIds = apiService.getExternalIds(tmdbId, apiKey)
                imdbId = externalIds.imdb_id
            } catch (_: Exception) {
                // Ignore if external ids fail
            }
        }

        val year = parseYear(details.release_date)
        val shortDescription = parseShortDescription(details.tagline, details.overview)
        val posterUrl = details.poster_path?.let { "https://image.tmdb.org/t/p/w342$it" } ?: ""

        val entity = MovieEntity(
            tmdbId = details.id,
            imdbId = imdbId,
            title = details.title,
            year = year,
            shortDescription = shortDescription,
            posterUrl = posterUrl,
            addedAt = System.currentTimeMillis()
        )

        withContext(Dispatchers.IO) {
            movieDao.insertMovie(entity)
        }
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.deleteMovie(movie)
        }
    }

    companion object {
        fun parseYear(releaseDate: String?): Int {
            if (releaseDate.isNullOrBlank()) return 0
            return releaseDate.take(4).toIntOrNull() ?: 0
        }

        fun parseShortDescription(tagline: String?, overview: String?): String {
            return if (!tagline.isNullOrBlank()) {
                tagline
            } else {
                overview ?: ""
            }
        }
    }
}
