package com.nectarofthepeople.spectoroster

import android.content.Context
import com.nectarofthepeople.spectoroster.data.remote.RetrofitConverterFactory
import com.nectarofthepeople.spectoroster.data.local.AppDatabase
import com.nectarofthepeople.spectoroster.data.remote.TmdbApiService
import com.nectarofthepeople.spectoroster.data.repository.MovieRepository
import com.nectarofthepeople.spectoroster.data.store.ApiKeyStore
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class AppContainer(context: Context) {
    val apiKeyStore: ApiKeyStore by lazy {
        ApiKeyStore(context)
    }

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit: Retrofit by lazy {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(RetrofitConverterFactory.create(json, contentType))
            .build()
    }

    private val tmdbApiService: TmdbApiService by lazy {
        retrofit.create(TmdbApiService::class.java)
    }

    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    val movieRepository: MovieRepository by lazy {
        MovieRepository(tmdbApiService, database.movieDao())
    }
}
