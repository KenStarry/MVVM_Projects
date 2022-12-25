package com.example.books_app.feature_books.presentation.books.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.books_app.feature_books.domain.model.Book
import com.example.books_app.feature_books.domain.model.Response
import com.example.books_app.feature_books.domain.repository.AddBookResponse
import com.example.books_app.feature_books.domain.repository.BooksResponse
import com.example.books_app.feature_books.domain.repository.DeleteBookResponse
import com.example.books_app.feature_books.domain.use_cases.BookUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//  private set - defines that the variable has a public getter but has a private setter
@HiltViewModel
class BooksViewModel @Inject constructor(
    private val useCases: BookUseCases
) : ViewModel() {

    var booksResponse by mutableStateOf<BooksResponse>(Response.Loading)
        private set

    var addBookResponse by mutableStateOf<AddBookResponse>(Response.Success(false))
        private set

    var deleteBookResponse by mutableStateOf<DeleteBookResponse>(Response.Success(false))
        private set

    var openDialog by mutableStateOf(false)
        private set

    init {
        getBooks()
    }

    private fun getBooks() {

        viewModelScope.launch {
            useCases.getBooks().collect { response ->
                booksResponse = response
            }
        }
    }

    //  Add a book
    fun addBook(title: String, author: String) {

        viewModelScope.launch {
            addBookResponse = Response.Loading
            useCases.addBook(title, author)
        }
    }

    fun deleteBook(bookId: String) {

        viewModelScope.launch {
            deleteBookResponse = Response.Loading
            useCases.deleteBook(bookId)
        }
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}




























