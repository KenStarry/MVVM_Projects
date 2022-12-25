package com.example.books_app.feature_books.domain.use_cases

import com.example.books_app.feature_books.domain.repository.BooksRepository

class GetBooks(
    private val repo: BooksRepository
) {
    operator fun invoke() = repo.getBooksFromFirestore()
}