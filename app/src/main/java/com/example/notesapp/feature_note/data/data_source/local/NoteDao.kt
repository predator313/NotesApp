package com.example.notesapp.feature_note.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("select * from t_note")
    fun getAllNotes():Flow<List<Note>>   //we don't make this function suspend because it return
    //flow if a query function return flow or livedata we don't require suspend function

    @Query("select * from t_note where id=:id")
    suspend fun getNoteById(id:Int):Note?   //here return type is not livedata or flow so it is suspend

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(note: Note)

    @Delete
    suspend fun deleteNode(note: Note)
}