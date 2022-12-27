package com.example.ticky.di

import com.example.ticky.core.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //  Firestore instance
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    //  Groups collection instance
    @Provides
    @Singleton
    fun provideGroupsReference(
        db: FirebaseFirestore
    ) = db.collection(Constants.GROUP_COLLECTION)

}






































