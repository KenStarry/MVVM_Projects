package com.example.cleanarchitecturenotes.feature_note.domain.repository

import com.example.cleanarchitecturenotes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    //  Get all notes
    fun getNotes(): Flow<List<Note>>

    //  Get note by id
    suspend fun getNoteById(id: Int): Note?

    //  insert a note
    suspend fun insertNote(note: Note)

    //  delete a note
    suspend fun deleteNote(note: Note)
}