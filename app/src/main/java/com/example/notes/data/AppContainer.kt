package com.example.notes.data

import android.content.Context

interface AppContainer {
    val notesRepository: NotesRepository
    val userPreferencesRepository: UserPreferencesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val notesRepository: NotesRepository by lazy {
        NotesRepository(NotesDatabase.getDatabase(context).noteDao())
    }

    override val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(context)
    }
}
