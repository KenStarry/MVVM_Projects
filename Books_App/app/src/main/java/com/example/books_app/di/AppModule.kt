package com.example.books_app.di

import com.example.books_app.core.Constants
import com.example.books_app.feature_books.data.repository.BooksRepositoryImpl
import com.example.books_app.feature_books.domain.repository.BooksRepository
import com.example.books_app.feature_books.domain.use_cases.AddBook
import com.example.books_app.feature_books.domain.use_cases.BookUseCases
import com.example.books_app.feature_books.domain.use_cases.DeleteBook
import com.example.books_app.feature_books.domain.use_cases.GetBooks
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //  provide our firestore database
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    //  Provide books reference
    @Provides
    fun provideBooksReference(
        db: FirebaseFirestore
    ) = db.collection(Constants.BOOKS)

    //  provide the repository implementation
    @Provides
    fun provideBooksRepository(
        booksRef: CollectionReference
    ) : BooksRepository = BooksRepositoryImpl(booksRef)

    //  provide all use cases
    @Provides
    fun provideUseCases(
        repository: BooksRepository
    ) = BookUseCases(
        addBook = AddBook(repository),
        deleteBook = DeleteBook(repository),
        getBooks = GetBooks(repository)
    )
}
























