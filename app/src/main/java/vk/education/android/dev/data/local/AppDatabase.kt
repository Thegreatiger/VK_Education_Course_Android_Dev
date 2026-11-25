package vk.education.android.dev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppDetailsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDetailsDao(): AppDetailsDao
}