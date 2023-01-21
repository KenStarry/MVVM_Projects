package com.example.books_app.feature_books.presentation.books.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.example.books_app.core.Constants
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAlertDialog(
    dialogTitle: String,
    bookTitle: String,
    bookAuthor: String,
    focusRequester: FocusRequester,
    onBookTitleChange: (newTitle: String) -> Unit,
    onBookAuthorChange: (newAuthor: String) -> Unit,
    onDialogClosed: () -> Unit,
    addBook: (title: String, author: String) -> Unit
) {

    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column {
                TextField(
                    value = bookTitle,
                    onValueChange = { onBookTitleChange(it) },
                    placeholder = {
                        Text(text = Constants.BOOK_TITLE)
                    },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                )
                
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = bookAuthor,
                    onValueChange = { onBookAuthorChange(it) },
                    placeholder = {
                        Text(text = Constants.AUTHOR)
                    },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onDialogClosed()
                addBook(bookTitle, bookAuthor)
            }) {
                
                Text(text = Constants.ADD)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDialogClosed()
            }) {

                Text(text = Constants.DISMISS)
            }
        }
    )
}