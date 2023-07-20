package com.example.roomlist.app

import android.app.Application
import com.example.db.datasource.AppDatabase
import com.example.libdb.repository.EmpresaRepository


class EmpresaApp : Application()  {

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { EmpresaRepository(database.EmpresaDao()) }
}