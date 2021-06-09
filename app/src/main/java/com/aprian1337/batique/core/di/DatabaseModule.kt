package com.aprian1337.batique.core.di

import android.content.Context
import androidx.room.Room
import com.aprian1337.batique.core.data.source.local.room.AppDatabase
import com.aprian1337.batique.core.data.source.local.room.BatikDao
import com.aprian1337.batique.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, Constants.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideBatikDao(database: AppDatabase): BatikDao = database.batikDao()
}