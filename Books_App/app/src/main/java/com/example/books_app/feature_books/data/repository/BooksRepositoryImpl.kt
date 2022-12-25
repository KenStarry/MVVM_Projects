package com.example.books_app.feature_books.data.repository

import com.example.books_app.core.Constants
import com.example.books_app.feature_books.domain.model.Book
import com.example.books_app.feature_books.domain.model.Response
import com.example.books_app.feature_books.domain.repository.AddBookResponse
import com.example.books_app.feature_books.domain.repository.BooksRepository
import com.example.books_app.feature_books.domain.repository.BooksResponse
import com.example.books_app.feature_books.domain.repository.DeleteBookResponse
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//  providing the actual implementation of BooksRepository
class BooksRepositoryImpl @Inject constructor(
    private val booksRef: CollectionReference
) : BooksRepository {

    //  Getting all books from firestore
    override fun getBooksFromFirestore(): Flow<BooksResponse> = callbackFlow {

        //  listener for the books
        val snapshotListener = booksRef.orderBy(Constants.TITLE).addSnapshotListener { snapshot, error ->

            val booksResponse = if (snapshot != null) {
                val books = snapshot.toObjects(Book::class.java)
                Response.Success(books)
            } else {
                Response.Failure(error)
            }

            trySend(booksResponse)
        }

        awaitClose { snapshotListener.remove() }
    }

    //  Add book to firestore
    override suspend fun addBookToFirestore(title: String, author: String): AddBookResponse {
        return try {

            val id = booksRef.document().id
            val book = Book(
                id = id,
                title = title,
                author = author
            )
            booksRef.document(id).set(book).await()
            Response.Success(true)

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    //  delete book from firestore
    override suspend fun deleteBookFromFirestore(bookId: String): DeleteBookResponse {

        return try {
            booksRef.document(bookId).delete().await()
            Response.Success(true)

        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}








