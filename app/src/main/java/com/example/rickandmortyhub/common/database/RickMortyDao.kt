package com.example.rickandmortyhub.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.model.Episode
import com.example.rickandmortyhub.common.model.Location

@Dao
interface RickMortyDao {

    @Query("SELECT * FROM CharactersTable")
    suspend fun getCharacters(): List<CharacterDb>

    @Query("SELECT * FROM LocationsTable")
    suspend fun getLocations(): List<Location>

    @Query("SELECT * FROM EpisodesTable")
    suspend fun getEpisodes(): List<Episode>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocations(locations: List<Location>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<Episode>)
}