package com.example.libdb.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//paso 2
@Entity
data class Empresa(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nombre") val nombre: String?,
    @ColumnInfo(name = "direccion") val direccion: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "telefono") val telefono: String?,
    @ColumnInfo(name = "urlImagen") val urlImagen: String?,
)
