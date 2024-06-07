package com.capstone.yukonek.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 2, exportSchema = false)
abstract class YuKonekDatabase : RoomDatabase(){
    companion object{
        @Volatile
        private var INSTANCE: YuKonekDatabase? = null
        @JvmStatic
        fun getInstance(context: Context): YuKonekDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    YuKonekDatabase::class.java, "yu_konek_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }

            }
        }
    }
}