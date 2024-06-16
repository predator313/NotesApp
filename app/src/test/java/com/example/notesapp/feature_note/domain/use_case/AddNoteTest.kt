package com.example.notesapp.feature_note.domain.use_case

import com.example.notesapp.feature_note.data.repository.FakeNoteRepository
import com.example.notesapp.feature_note.domain.model.InvalidNoteException
import com.example.notesapp.feature_note.domain.model.Note
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class AddNoteTest{
    private lateinit var addNote: AddNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp(){
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
    }

    @Test
    fun `throw invalid exception when the title is empty`(){
        val note = Note(
            title = "",
            content = "content",
            id = 1,
            timeStamp = System.currentTimeMillis(),
            color = 1
        )
        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }
        assertThat(exception).hasMessageThat().isEqualTo("The Note Title can't be Empty or Blank")
    }

    @Test
    fun `throw invalid exception when the content is empty`(){
        val note = Note(
            title = "title",
            content = "",
            id = 1,
            timeStamp = System.currentTimeMillis(),
            color = 1
        )
        val exception = assertThrows(InvalidNoteException::class.java) {
            runBlocking {
                addNote(note)
            }
        }
        assertThat(exception).hasMessageThat().isEqualTo("The Note Content can't be Empty or Blank")
    }

    @Test
    fun `add note successfully when title and content is not empty`(){
        val note = Note(
            title = "title",
            content = "content",
            id = 1,
            timeStamp = System.currentTimeMillis(),
            color = 1
        )
        runBlocking {
            addNote(note)
        }
        val addedNote = runBlocking {
            fakeNoteRepository.getNoteById(note.id!!)
        }
        assertThat(addedNote).isNotNull()
        assertThat(addedNote?.title).isEqualTo(note.title)
        assertThat(addedNote?.content).isEqualTo(note.content)
    }

}