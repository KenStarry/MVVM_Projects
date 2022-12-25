package com.example.books_app.di

import com.example.books_app.core.Constants
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

}