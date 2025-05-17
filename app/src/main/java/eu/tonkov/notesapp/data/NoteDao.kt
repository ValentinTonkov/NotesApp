package eu.tonkov.notesapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll() : Flow<List<Note>>

    @Insert
    suspend fun insert(note: Note)
}