package com.aprian1337.batique.di

import com.aprian1337.core.domain.usecase.BatikInteractor
import com.aprian1337.core.domain.usecase.BatikUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideUseCase(interactor: BatikInteractor): BatikUseCase
}