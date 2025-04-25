package com.example.notes.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_preferences")

class UserPreferencesRepository(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private val DARK_MODE_ENABLED = booleanPreferencesKey("dark_mode_enabled")
    }

    val darkModeEnabled: Flow<Boolean> = dataStore.data
        .map { preferences -> preferences[DARK_MODE_ENABLED] ?: false }

    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_MODE_ENABLED] = enabled
        }
    }
}
