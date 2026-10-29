package com.nectarofthepeople.spectoroster.data.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "api_key_prefs")

class ApiKeyStore(private val context: Context) {
    private object PreferencesKeys {
        val API_KEY = stringPreferencesKey("api_key")
    }

    val apiKey: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.API_KEY]
        }

    suspend fun saveApiKey(key: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.API_KEY] = key
        }
    }

    suspend fun clearApiKey() {
        context.dataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.API_KEY)
        }
    }
}
