package com.aprian1337.batique.core.di

import com.aprian1337.batique.core.data.BatikRepository
import com.aprian1337.batique.core.domain.repository.IBatikRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: BatikRepository): IBatikRepository

}