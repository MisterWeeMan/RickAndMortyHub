package com.example.rickandmortyhub.common.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EpisodesTable")
data class EpisodeDb(
    @PrimaryKey val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)