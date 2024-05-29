package com.example.notesapp.feature_note.data.repository

import com.example.notesapp.feature_note.data.data_source.local.NoteDao
import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
):NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
       return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNode(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNode(note)
    }
}