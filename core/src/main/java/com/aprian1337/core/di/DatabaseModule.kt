package com.aprian1337.core.di

import android.content.Context
import androidx.room.Room
import com.aprian1337.core.data.source.local.room.AppDatabase
import com.aprian1337.core.data.source.local.room.BatikDao
import com.aprian1337.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("aprian1337".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context, AppDatabase::class.java, Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }

    @Provides
    fun provideBatikDao(database: AppDatabase): BatikDao = database.batikDao()
}