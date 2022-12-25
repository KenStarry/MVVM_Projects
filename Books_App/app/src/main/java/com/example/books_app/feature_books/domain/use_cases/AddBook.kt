package com.example.books_app.feature_books.domain.use_cases

import com.example.books_app.feature_books.domain.repository.BooksRepository

//  Add Book to firestore
class AddBook(
    private val repo: BooksRepository
) {

    suspend operator fun invoke(
        title: String,
        author: String
    ) {
        repo.addBookToFirestore(title, author)
    }
}