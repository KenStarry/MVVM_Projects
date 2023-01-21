package com.example.books_app.feature_books.presentation.books.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.books_app.core.Constants

@Composable
fun AddBookFab(
    openDialog: () -> Unit
) {

    FloatingActionButton(
        onClick = { openDialog() },
        containerColor = MaterialTheme.colorScheme.primary
    ) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = Constants.ADD_BOOK
        )

    }

}