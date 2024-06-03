package com.example.notesapp.feature_note.domain.model

import androidx.lifecycle.ViewModel
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.ui.theme.BabyBlue
import com.example.notesapp.ui.theme.LightGreen
import com.example.notesapp.ui.theme.RedOrange
import com.example.notesapp.ui.theme.RedPink
import com.example.notesapp.ui.theme.Violet

@Entity(tableName = "t_note")
data class Note(
    val title:String,
    val content:String,
    val timeStamp:Long,
    val color:Int,
    @PrimaryKey val id:Int?=null

){
    //companion object as the hard coded one
    companion object{
        val noteColors= listOf(RedOrange, RedPink, Violet, BabyBlue, LightGreen)
    }
}
class InvalidNoteException( message:String):Exception(message)

