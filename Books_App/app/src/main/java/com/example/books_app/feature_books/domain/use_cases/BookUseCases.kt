package com.example.books_app.feature_books.domain.use_cases

data class BookUseCases(
    val addBook: AddBook,
    val deleteBook: DeleteBook,
    val getBooks: GetBooks
)
