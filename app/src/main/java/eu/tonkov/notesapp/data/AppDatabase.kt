package eu.tonkov.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var Instance : AppDatabase? = null


        fun getInstance(context: Context) : AppDatabase{
            return Instance ?: synchronized (this){
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                    .also {
                        Instance = it
                    }
            }
        }

    }
}