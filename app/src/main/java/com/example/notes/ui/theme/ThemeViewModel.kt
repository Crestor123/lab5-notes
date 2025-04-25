package com.example.notes.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    val preferences = userPreferencesRepository

    val isDarkMode: StateFlow<Boolean> =
        userPreferencesRepository.isDarkMode.map { it }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = false
        )

    fun setDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            preferences.saveLayoutPreference(isDarkMode)
        }
    }
}