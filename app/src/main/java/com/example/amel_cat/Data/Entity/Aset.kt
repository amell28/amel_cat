package com.example.amel_cat.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aset")
data class Aset(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaAset: String,
    val jenisAset: String,
    val jumlahAset: Int,
    val lokasiAset: String
)