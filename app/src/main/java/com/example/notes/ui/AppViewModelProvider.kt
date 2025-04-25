package com.example.notes.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.notes.NotesApplication
import com.example.notes.ui.theme.ThemeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(notesApplication().container.notesRepository)
        }

        initializer {
            ThemeViewModel(notesApplication().userPreferencesRepository)
        }
    }
}

fun CreationExtras.notesApplication(): NotesApplication = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)