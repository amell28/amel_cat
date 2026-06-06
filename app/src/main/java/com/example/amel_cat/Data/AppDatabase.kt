package com.example.amel_cat.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.amel_cat.Data.Dao.AsetDao
import com.example.amel_cat.Data.Dao.StaffDao
import com.example.amel_cat.Data.Entity.Aset
import com.example.amel_cat.Data.Entity.Staff

@Database(entities = [Aset::class, Staff::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun asetDao(): AsetDao
    abstract fun staffDao(): StaffDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inventaris_desa_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}