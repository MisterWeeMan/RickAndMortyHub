package com.example.rickandmortyhub.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.database.utils.RoomConverters
import com.example.rickandmortyhub.common.model.Episode
import com.example.rickandmortyhub.common.model.Location

@Database(entities = [CharacterDb::class, Episode::class, Location::class], version = 1)
@TypeConverters(RoomConverters::class)
abstract class RickMortyDatabase: RoomDatabase() {

    abstract fun rickMortyDao(): RickMortyDao
}