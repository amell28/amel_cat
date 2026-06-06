package com.example.amel_cat.Data.Dao

import androidx.room.*
import com.example.amel_cat.Data.Entity.Staff
import kotlinx.coroutines.flow.Flow

@Dao
interface StaffDao {
    @Query("SELECT * FROM staff_table ORDER BY id ASC")
    fun getAllStaff(): Flow<List<Staff>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(staff: Staff)

    @Delete
    suspend fun delete(staff: Staff)
}