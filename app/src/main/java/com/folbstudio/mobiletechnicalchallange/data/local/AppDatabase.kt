package com.folbstudio.mobiletechnicalchallange.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.folbstudio.mobiletechnicalchallange.models.AdEntity
import com.folbstudio.mobiletechnicalchallange.utils.databaseName

@Database(entities = [AdEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun adDao(): AdDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, databaseName)
                .fallbackToDestructiveMigration()
                .addCallback(object : Callback() { })
                .build()
        }
    }


}