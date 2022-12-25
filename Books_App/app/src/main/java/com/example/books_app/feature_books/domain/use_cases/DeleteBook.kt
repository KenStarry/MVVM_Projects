package com.example.books_app.feature_books.domain.use_cases

import com.example.books_app.feature_books.domain.repository.BooksRepository

//  Delete Book From Firestore
class DeleteBook(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(bookId: String) {
        repo.deleteBookFromFirestore(bookId)
    }
}