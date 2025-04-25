package com.example.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val darkModeEnabled: Flow<Boolean> = userPreferencesRepository.darkModeEnabled

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkMode(enabled)
        }
    }
}
