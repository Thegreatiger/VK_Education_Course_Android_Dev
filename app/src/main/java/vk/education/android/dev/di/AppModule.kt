package vk.education.android.dev.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vk.education.android.dev.data.repository.AppRepositoryImpl
import vk.education.android.dev.domain.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindRepository(impl: AppRepositoryImpl): AppRepository
}