package com.example.notesapp.feature_note.presentation.utils

sealed class Screen(val route:String){
    //this class is used to handle all the navigation related work
    object NoteScreen:Screen("notes_screen")
    object AddEditNoteScreen:Screen("add_edit_note_screen")

}
