package com.example.books_app.feature_books.domain.repository

import com.example.books_app.feature_books.domain.model.Book
import com.example.books_app.feature_books.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Books = List<Book>
typealias BooksResponse = Response<Books>
typealias AddBookResponse = Response<Boolean>
typealias DeleteBookResponse = Response<Boolean>

interface BooksRepository {

    //  Get all books
    fun getBooksFromFirestore(): Flow<BooksResponse>

    //  Add a book
    suspend fun addBookToFirestore(
        title: String,
        author: String
    ): AddBookResponse

    //  Delete a book
    suspend fun deleteBookFromFirestore(
        bookId: String
    ): DeleteBookResponse

}












































