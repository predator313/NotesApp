package com.example.notesapp.feature_note.presentation.notes

import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.utils.NoteOrder

sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder):NotesEvent()
    data class DeleteNote(val note: Note):NotesEvent()

    //inside sealed class data class is used to store the state which changes

    //object are used for singleton ,the state which doesn't change

    object RestoreNote:NotesEvent()
    object ToggleOrderSection:NotesEvent()

}
