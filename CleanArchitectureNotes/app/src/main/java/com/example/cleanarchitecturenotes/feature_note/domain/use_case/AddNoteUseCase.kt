package com.example.cleanarchitecturenotes.feature_note.domain.use_case

import com.example.cleanarchitecturenotes.feature_note.domain.model.InvalidNoteException
import com.example.cleanarchitecturenotes.feature_note.domain.model.Note
import com.example.cleanarchitecturenotes.feature_note.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {

        //  Validate whether input is valid (since this is business logic, we do it here)
        if (note.title.isBlank())
            throw InvalidNoteException("Note title cannot be blank!")
        if (note.content.isBlank())
            throw InvalidNoteException("Content cannot be blank")

        repository.insertNote(note)
    }
}