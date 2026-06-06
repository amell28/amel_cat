package com.example.amel_cat.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff_table")
data class Staff(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val namaStaff: String,
    val jabatan: String,
    val nomorKontak: String
)