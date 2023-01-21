package com.example.books_app.feature_books.presentation.books.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.books_app.core.Constants
import com.example.books_app.feature_books.domain.use_cases.DeleteBook

@Composable
fun DeleteIcon(
    deleteBook: () -> Unit
) {

    IconButton(onClick = deleteBook) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = Constants.DELETE_BOOK
        )
    }

}