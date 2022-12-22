package com.example.cleanarchitecturenotes.feature_note.domain.use_case

import com.example.cleanarchitecturenotes.feature_note.domain.model.Note
import com.example.cleanarchitecturenotes.feature_note.domain.repository.NoteRepository
import com.example.cleanarchitecturenotes.feature_note.domain.util.NoteOrder
import com.example.cleanarchitecturenotes.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//  use cases should usually have only one public function that executes immediately when called
class GetNotesUseCase(
    private val repository: NoteRepository
) {

    //  invoke functions don't have to be called - useful in a class having a single method
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {

        //  map the list we get from the repository to a new sorted list
        return repository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }

                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}