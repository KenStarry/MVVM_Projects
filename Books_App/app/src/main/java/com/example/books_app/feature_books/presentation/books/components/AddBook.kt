package com.example.books_app.feature_books.presentation.books.components

import android.widget.ProgressBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.books_app.core.components.MyProgressBar
import com.example.books_app.feature_books.domain.model.Response
import com.example.books_app.feature_books.presentation.books.viewmodel.BooksViewModel

@Composable
fun AddBook(
    viewModel: BooksViewModel = hiltViewModel()
) {

    when (val addBookResponse = viewModel.addBookResponse) {
        is Response.Loading -> MyProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> println(addBookResponse.e)
    }

}