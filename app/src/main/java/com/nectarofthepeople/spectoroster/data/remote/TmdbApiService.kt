package com.nectarofthepeople.spectoroster.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String
    ): TmdbSearchResponse

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): TmdbMovieDetails

    @GET("movie/{id}/external_ids")
    suspend fun getExternalIds(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): TmdbExternalIds
}
