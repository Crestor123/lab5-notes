package com.example.notes.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
){
    private companion object {
        val DARK_MODE_ENABLED = booleanPreferencesKey("dark_mode_enabled")
    }
}