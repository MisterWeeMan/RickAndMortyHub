package com.example.rickandmortyhub.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.database.model.EpisodeDb
import com.example.rickandmortyhub.common.database.model.LocationDb

@Dao
interface RickMortyDao {

    @Query("SELECT * FROM CharactersTable")
    suspend fun getCharacters(): List<CharacterDb>

    @Query("SELECT * FROM LocationsTable")
    suspend fun getLocations(): List<LocationDb>

    @Query("SELECT * FROM EpisodesTable")
    suspend fun getEpisodes(): List<EpisodeDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocations(locations: List<LocationDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<EpisodeDb>)
}