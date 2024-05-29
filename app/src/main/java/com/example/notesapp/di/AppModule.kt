package com.example.notesapp.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.feature_note.data.data_source.local.NoteDataBase
import com.example.notesapp.feature_note.data.repository.NoteRepositoryImpl
import com.example.notesapp.feature_note.domain.repository.NoteRepository
import com.example.notesapp.feature_note.domain.use_case.DeleteNote
import com.example.notesapp.feature_note.domain.use_case.GetNotes
import com.example.notesapp.feature_note.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDataBase{
        return Room.databaseBuilder(app,
            NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME
            )
            .build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDataBase):NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository):NoteUseCase{
        return NoteUseCase(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository)
        )
    }
}