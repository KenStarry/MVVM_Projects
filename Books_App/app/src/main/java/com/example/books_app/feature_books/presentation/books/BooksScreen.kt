package com.example.books_app.feature_books.presentation.books

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.books_app.core.components.MyTopBar
import com.example.books_app.feature_books.presentation.books.components.*
import com.example.books_app.feature_books.presentation.books.viewmodel.BooksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = { MyTopBar() },
        floatingActionButton = {
            AddBookFab {
                viewModel.openDialog()
            }
        }
    ) { contentPadding ->

        Books(
            viewModel = viewModel,
            booksContent = { books ->
                BooksContent(
                    padding = contentPadding,
                    books = books,
                    deleteBook = { id ->
                        viewModel.deleteBook(id)
                    }
                )

                AddBookAlertDialog(
                    openDialog = viewModel.openDialog,
                    closeDialog = {
                        viewModel.closeDialog()
                    },
                    addBook = {title, author ->
                        viewModel.addBook(title, author)
                    }
                )
            }
        )

    }

    AddBook()
    DeleteBook()
}