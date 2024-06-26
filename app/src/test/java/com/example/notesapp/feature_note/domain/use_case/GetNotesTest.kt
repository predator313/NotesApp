package com.example.notesapp.feature_note.domain.use_case

import com.example.notesapp.feature_note.data.repository.FakeNoteRepository
import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.utils.NoteOrder
import com.example.notesapp.feature_note.domain.utils.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetNotesTest{
    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepository: FakeNoteRepository
    @Before
    fun setUp(){
        fakeNoteRepository = FakeNoteRepository()
        getNotes = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    id = index,
                    timeStamp = index.toLong(),
                    content = c.toString(),
                    color = index
                )
            )
        }
        notesToInsert.shuffle()   //shuffle whole list in random manner

        runBlocking {
            notesToInsert.forEach {
                fakeNoteRepository.insertNote(it)
            }
        }


    }

    @Test
    fun `Order notes by title ascending, is correct Order`(){
        runBlocking {
            val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()

            for(i in 0..notes.size-2){
                assertThat(
                    notes[i].title
                ).isLessThan(notes[i+1].title)
            }
        }
    }

    @Test
    fun `Order notes by data ascending, is correct Order`(){
        runBlocking {
            val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()

            for(i in 0..notes.size-2){
                assertThat(
                    notes[i].timeStamp
                ).isLessThan(notes[i+1].timeStamp)
            }
        }
    }
    @Test
    fun `Order notes by color ascending, is correct Order`(){
        runBlocking {
            val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()

            for(i in 0..notes.size-2){
                assertThat(
                    notes[i].color
                ).isLessThan(notes[i+1].color)
            }
        }
    }

    @Test
    fun `Order notes by title descending, is correct Order`(){
        runBlocking {
            val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()

            for(i in 0..notes.size-2){
                assertThat(
                    notes[i+1].title
                ).isLessThan(notes[i].title)
            }
        }
    }
    @Test
    fun `Order notes by date descending, is correct Order`(){
        runBlocking {
            val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()

            for(i in 0..notes.size-2){
                assertThat(
                    notes[i+1].timeStamp
                ).isLessThan(notes[i].timeStamp)
            }
        }
    }

    @Test
    fun `Order notes by color descending, is correct Order`(){
        runBlocking {
            val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()

            for(i in 0..notes.size-2){
                assertThat(
                    notes[i].color
                ).isGreaterThan(notes[i+1].color)
            }
        }
    }
}