package com.example.rickandmortyhub.network.model.location


import com.example.rickandmortyhub.network.model.Info
import com.google.gson.annotations.SerializedName

data class LocationsInfo(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val locations: List<Location>
)