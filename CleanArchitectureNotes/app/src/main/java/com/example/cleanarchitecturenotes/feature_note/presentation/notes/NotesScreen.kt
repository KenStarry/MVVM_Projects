package com.example.cleanarchitecturenotes.feature_note.presentation.notes

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.cleanarchitecturenotes.feature_note.presentation.notes.components.NoteItem
import com.example.cleanarchitecturenotes.feature_note.presentation.notes.components.OrderSection
import com.example.cleanarchitecturenotes.feature_note.presentation.notes.viewmodel.NotesViewModel
import com.example.cleanarchitecturenotes.feature_note.presentation.util.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    navHostController: NavHostController,
    viewModel: NotesViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.AddEditNote.route)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Your Notes",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                actions = {
                    IconButton(onClick = {
                        //  changing the order
                        viewModel.onEvent(NoteEvents.ToggleOrderSection)
                    }) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Sort"
                        )
                    }
                }
            )
        }

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {

                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NoteEvents.Order(it))
                    }
                )

            }

            Spacer(modifier = Modifier.height(24.dp))

            //  list of notes
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navHostController.navigate(
                                    Screen.AddEditNote.route +
                                            "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            },
                        onDelete = {
                            viewModel.onEvent(NoteEvents.DeleteNote(note))
                            scope.launch {
                                //  show snack bar
                            }
                        }
                    )
                }
            }

        }

    }

}





















