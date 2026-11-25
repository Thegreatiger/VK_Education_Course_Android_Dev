package vk.education.android.dev.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vk.education.android.dev.data.mapper.AppDetailsMapper
import vk.education.android.dev.data.repository.AppDetailsRepositoryImpl
import vk.education.android.dev.domain.AppDetailsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindAppDetailsRepository(impl: AppDetailsRepositoryImpl): AppDetailsRepository

    companion object {
        @Provides
        @Singleton
        fun provideAppDetailsMapper(): AppDetailsMapper = AppDetailsMapper()
    }
}