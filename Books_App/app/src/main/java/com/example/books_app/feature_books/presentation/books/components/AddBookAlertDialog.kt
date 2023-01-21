package com.example.books_app.feature_books.presentation.books.components

import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import com.example.books_app.core.Constants

@Composable
fun AddBookAlertDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    addBook: (title: String, author: String) -> Unit
) {

    if (openDialog) {

        var title by remember { mutableStateOf(Constants.NO_VALUE) }
        var author by remember { mutableStateOf(Constants.NO_VALUE) }

        val focusRequester = FocusRequester()

        MyAlertDialog(
            dialogTitle = Constants.ADD_BOOK,
            bookTitle = title,
            bookAuthor = author,
            focusRequester = focusRequester,
            onBookTitleChange = {
                title = it
            },
            onBookAuthorChange = {
                author = it
            },
            onDialogClosed = {
                closeDialog()
            },
            addBook = {t, a ->
                addBook(t, a)
            }
        )
    }

}






















