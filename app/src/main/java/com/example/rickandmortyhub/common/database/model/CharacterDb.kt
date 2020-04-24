package com.example.rickandmortyhub.common.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharactersTable")
data class CharacterDb(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val nameOriginLocation: String,
    val urlOriginLocation: String,
    val nameLocation: String,
    val urlLocation: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)