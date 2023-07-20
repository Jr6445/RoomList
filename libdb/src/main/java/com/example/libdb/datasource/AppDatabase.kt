package com.example.db.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.db.dao.UserDao
import com.example.db.model.entity.User
import com.example.libdb.dao.EmpresaDao
import com.example.libdb.model.entity.Empresa

@Database(entities = [Empresa::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun EmpresaDao(): EmpresaDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}