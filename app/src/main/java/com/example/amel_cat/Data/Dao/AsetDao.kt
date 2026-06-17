package com.example.amel_cat.Data.Dao

import androidx.room.*
import com.example.amel_cat.Data.Entity.Aset
import kotlinx.coroutines.flow.Flow

@Dao
interface AsetDao {
    @Query("SELECT * FROM aset ORDER BY id ASC")
    fun getAllAset(): Flow<List<Aset>>

    @Query("SELECT * FROM aset WHERE id = :id LIMIT 1")
    suspend fun getAsetById(id: Int): Aset?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAset(aset: Aset)

    @Delete
    suspend fun deleteAset(aset: Aset)
}