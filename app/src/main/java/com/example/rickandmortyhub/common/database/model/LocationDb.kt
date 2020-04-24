package com.example.rickandmortyhub.common.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LocationsTable")
data class LocationDb(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)