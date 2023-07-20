package com.example.libdb.repository

import androidx.annotation.WorkerThread
import com.example.libdb.dao.EmpresaDao
import com.example.libdb.model.entity.Empresa
import kotlinx.coroutines.flow.Flow

class EmpresaRepository(private val empresaDao: EmpresaDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allUsers: Flow<List<Empresa>> = empresaDao.getAllFlow()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Empresa) {
        empresaDao.insertAll(word)
    }
}