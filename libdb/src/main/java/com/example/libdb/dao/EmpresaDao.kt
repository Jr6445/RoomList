package com.example.libdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.libdb.model.entity.Empresa
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpresaDao {
    @Query("SELECT * FROM empresa")
    fun getAll(): List<Empresa>

    @Query("SELECT * FROM empresa")
    fun getAllFlow(): Flow<List<Empresa>>



    @Insert
    fun insertAll(vararg empresa: Empresa)


}