package com.aprian1337.batique.di

import com.aprian1337.core.domain.usecase.BatikUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavDependencies {
    fun provideUseCase(): BatikUseCase
}