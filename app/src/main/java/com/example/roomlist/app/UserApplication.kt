package com.example.roomlist.app

import android.app.Application
import com.example.db.datasource.AppDatabase

import com.example.libdb.repository.EmpresaRepository

class UserApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { EmpresaRepository(database.EmpresaDao()) }
}