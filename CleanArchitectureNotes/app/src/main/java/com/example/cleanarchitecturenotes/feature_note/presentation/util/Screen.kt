package com.example.cleanarchitecturenotes.feature_note.presentation.util

sealed class Screen(
    val route: String
) {
    object Notes : Screen("notes_screen")
    object AddEditNote : Screen("add_edit_note_screen")
}
