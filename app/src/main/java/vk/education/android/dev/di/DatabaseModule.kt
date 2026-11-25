package vk.education.android.dev.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vk.education.android.dev.data.local.AppDatabase
import vk.education.android.dev.data.local.AppDetailsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }

    @Provides
    @Singleton
    fun provideAppDetailsDao(db: AppDatabase): AppDetailsDao = db.appDetailsDao()
}