package com.example.cleanarchitecturenotes.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecturenotes.feature_note.data.data_source.NoteDatabase
import com.example.cleanarchitecturenotes.feature_note.data.repository.NoteRepositoryImpl
import com.example.cleanarchitecturenotes.feature_note.domain.repository.NoteRepository
import com.example.cleanarchitecturenotes.feature_note.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //  provide an instance of our room database - will only be referenced once
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    //  provide our note repository
    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    //  provide our wrapper class for all the use cases
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
    }
}
































