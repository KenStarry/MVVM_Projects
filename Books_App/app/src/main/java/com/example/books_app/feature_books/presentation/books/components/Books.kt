package com.example.books_app.feature_books.presentation.books.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.books_app.core.components.MyProgressBar
import com.example.books_app.feature_books.domain.model.Response
import com.example.books_app.feature_books.domain.repository.Books
import com.example.books_app.feature_books.presentation.books.viewmodel.BooksViewModel

@Composable
fun Books(
    viewModel: BooksViewModel = hiltViewModel(),
    booksContent: @Composable (book: Books) -> Unit
) {

    when (val booksResponse = viewModel.booksResponse) {
        is Response.Loading -> MyProgressBar()
        is Response.Success -> booksContent(booksResponse.data)
        is Response.Failure -> println(booksResponse.e)
    }

}