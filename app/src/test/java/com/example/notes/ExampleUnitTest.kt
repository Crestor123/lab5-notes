package com.example.notes

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notes.data.NotesDatabase
import com.example.notes.data.NotesRepository
import com.example.notes.ui.HomeScreen
import com.example.notes.ui.HomeViewModel
import com.example.notes.ui.theme.NotesTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering.Context
import kotlin.contracts.ExperimentalContracts
