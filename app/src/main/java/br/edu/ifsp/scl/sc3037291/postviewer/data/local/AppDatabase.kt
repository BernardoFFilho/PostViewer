package br.edu.ifsp.scl.sc3037291.postviewer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalComment::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun localCommentDao(): LocalCommentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "postviewer_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
