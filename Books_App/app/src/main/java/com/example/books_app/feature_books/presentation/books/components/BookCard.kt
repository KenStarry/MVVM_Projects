package com.example.books_app.feature_books.presentation.books.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.books_app.core.Constants
import com.example.books_app.feature_books.domain.model.Book

@Composable
fun BookCard(
    book: Book,
    deleteBook: () -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                vertical = 4.dp,
                horizontal = 8.dp
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            hoveredElevation = 4.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                TextTitle(bookTitle = book.title ?: Constants.NO_VALUE)

                TextAuthor(bookAuthor = book.author ?: Constants.NO_VALUE)
            }
            
            Spacer(modifier = Modifier.weight(1f))

            DeleteIcon {
                deleteBook()
            }

        }

    }

}





























