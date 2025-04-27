package com.example.notes

import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import androidx.room.Room
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notes.data.Note
import com.example.notes.data.NoteDao
import com.example.notes.data.NotesDatabase
import com.example.notes.data.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class NoteDaoTest {
    lateinit var noteDao: NoteDao
    lateinit var noteDatabase : NotesDatabase
    lateinit var noteRepository : NotesRepository


    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        //noteDatabase = Room.inMemoryDatabaseBuilder(
            //context, NotesDatabase::class.java).build()
        //noteDao = noteDatabase.noteDao()
        noteRepository = NotesRepository(NotesDatabase.getDatabase(context).noteDao())

    }

    //@After
    //@Throws(IOException::class)
    //fun closeDb() {
    //    noteDatabase.close()
    //}

    suspend fun testInsert(note : Note) {
        noteRepository.insertNote(note)
    }

    //I have no clue how this is supposed to work
    @Test
    @Throws(Exception::class)
    fun testInsertNote() = runTest {
        val testNote : Note = Note(3, "Insert Test", "Insert Test", System.currentTimeMillis())
        launch { noteRepository.insertNote(testNote) }  //I have no idea if this works
        advanceUntilIdle()
        var allNotes : List<Note> = listOf()
        launch { allNotes = noteRepository.getAllNotesStream().toList().flatten() } //This should return something but doesn't?
        advanceUntilIdle()
        assert(noteRepository.getNoteStream(3).equals(testNote))
    }
}