package com.example.notesapp.feature_note.domain.use_case

import com.example.notesapp.feature_note.domain.model.InvalidNoteException
import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
){
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("The Note Title can't be Empty or Blank")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("The Note Content can't be Empty or Blank")
        }
        repository.insertNote(note)
    }
}
