package eu.tonkov.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tonkov.notesapp.data.AppDatabase
import eu.tonkov.notesapp.data.Note
import eu.tonkov.notesapp.data.NotesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application = application) {
    private val repository: NotesRepository
    val notesFlow : Flow<List<Note>>

    init {
        val noteDao = AppDatabase.getInstance(application).noteDao()
        repository = NotesRepository(noteDao)
        notesFlow = repository.allNotes
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }


}