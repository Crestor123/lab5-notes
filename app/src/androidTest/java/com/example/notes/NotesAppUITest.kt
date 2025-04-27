package com.example.notes

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notes.ui.HomeScreen
import com.example.notes.ui.theme.NotesTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get: Rule val composeTestRule = createComposeRule()

    @Test
    fun testAddNewNoteUI() {
        //Tests adding a new note, and checking that it is displayed

        composeTestRule.setContent {
            NotesTheme {
                HomeScreen()
            }
        }
        composeTestRule.onNodeWithTag("fab")
            .performClick()

        composeTestRule.onNodeWithTag("addDialog")
            .assertExists() //The dialog box is real

        //Insert a new note
        composeTestRule.onNodeWithTag("titleField")
            .performTextInput("Test Note")
        composeTestRule.onNodeWithTag("contentField")
            .performTextInput("Test Content")
        composeTestRule.onNodeWithText("Save")
            .performClick()

        composeTestRule.onNodeWithText("Test Note")
            .assertExists()
    }

    @Test
    fun testEditNoteUI() {
        //Tests editing and saving an existing note
        composeTestRule.setContent {
            NotesTheme {
                HomeScreen()
            }
        }

        //Create a note
        composeTestRule.onNodeWithTag("fab")
            .performClick()
        composeTestRule.onNodeWithTag("titleField")
            .performTextInput("Edit Note")
        composeTestRule.onNodeWithTag("contentField")
            .performTextInput("Test Content")
        composeTestRule.onNodeWithText("Save")
            .performClick()

        composeTestRule.onNodeWithText("Edit Note")
            .performClick()

        composeTestRule.onNodeWithTag("editDialog")
            .assertExists()

        composeTestRule.onNodeWithTag("contentField")
            .performTextReplacement("Edited Content")
        composeTestRule.onNodeWithText("Save")
            .performClick()

        composeTestRule.onNodeWithText("Edited Content")
            .assertExists()
    }

    @Test
    fun testDeleteNoteUI() {
        //Tests deleting a note
        composeTestRule.setContent {
            NotesTheme {
                HomeScreen()
            }
        }

        //Create a note
        composeTestRule.onNodeWithTag("fab")
            .performClick()
        composeTestRule.onNodeWithTag("titleField")
            .performTextInput("Delete Note")
        composeTestRule.onNodeWithTag("contentField")
            .performTextInput("Test Content")
        composeTestRule.onNodeWithText("Save")
            .performClick()

        composeTestRule.onNodeWithText("Delete Note")
            .performClick()

        composeTestRule.onNodeWithText("Delete")
            .performClick()

        composeTestRule.onNodeWithText("Delete Note")
            .assertDoesNotExist()
    }

    @Test
    fun testToggleDarkMode() {
        composeTestRule.setContent {
            NotesTheme {
                NotesApp()
            }
        }
       composeTestRule.onNodeWithTag("toggle")
           .performClick()

        //I have no idea how to test this
    }
}