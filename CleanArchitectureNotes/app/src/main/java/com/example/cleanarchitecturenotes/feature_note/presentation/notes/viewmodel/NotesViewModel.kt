package com.example.cleanarchitecturenotes.feature_note.presentation.notes.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturenotes.feature_note.domain.model.Note
import com.example.cleanarchitecturenotes.feature_note.domain.use_case.NoteUseCases
import com.example.cleanarchitecturenotes.feature_note.domain.util.NoteOrder
import com.example.cleanarchitecturenotes.feature_note.domain.util.OrderType
import com.example.cleanarchitecturenotes.feature_note.presentation.notes.NoteEvents
import com.example.cleanarchitecturenotes.feature_note.presentation.notes.models.NotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

//  The viewmodel makes use of our use cases
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    //  State that our UI will observe
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    //  reference to store our recently deleted note
    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    //  order items on load
    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    //  When an event is carried out on a note
    fun onEvent(event: NoteEvents) {

        when (event) {
            //  Order the notes
            is NoteEvents.Order -> {

                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
            }

            //  Delete a Note
            is NoteEvents.DeleteNote -> {
                viewModelScope.launch {
                    recentlyDeletedNote = event.note
                    noteUseCases.deleteNoteUseCase(event.note)
                }
            }

            //  Restore note after deletion
            is NoteEvents.RestoreNote -> {
                viewModelScope.launch {
                    //  add the note again
                    noteUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NoteEvents.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {

        getNotesJob?.cancel()

        getNotesJob = noteUseCases.getNotesUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }.launchIn(viewModelScope)
    }
}