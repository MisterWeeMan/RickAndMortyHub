package com.example.rickandmortyhub.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.database.model.EpisodeDb
import com.example.rickandmortyhub.common.database.model.LocationDb
import com.example.rickandmortyhub.common.database.utils.RoomConverters

@Database(entities = [CharacterDb::class, EpisodeDb::class, LocationDb::class], version = 1)
@TypeConverters(RoomConverters::class)
abstract class RickMortyDatabase: RoomDatabase() {

    abstract fun rickMortyDao(): RickMortyDao
}