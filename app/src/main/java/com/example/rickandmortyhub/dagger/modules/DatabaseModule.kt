package com.example.rickandmortyhub.dagger.modules

import android.content.Context
import androidx.room.Room
import com.example.rickandmortyhub.common.DATABASE_NAME
import com.example.rickandmortyhub.common.database.RickMortyDao
import com.example.rickandmortyhub.common.database.RickMortyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(
    private val applicationContext: Context
) {

    @Provides
    @Singleton
    fun provideDao(database: RickMortyDatabase): RickMortyDao {
        return database.rickMortyDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(): RickMortyDatabase {
        return Room.databaseBuilder(
            applicationContext,
            RickMortyDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}