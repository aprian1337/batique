package com.aprian1337.batique.core.di

import com.aprian1337.batique.core.domain.usecase.BatikInteractor
import com.aprian1337.batique.core.domain.usecase.BatikUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class MaInModule {
    @Binds
    @ViewModelScoped
    abstract fun provideUseCase(interactor: BatikInteractor): BatikUseCase
}