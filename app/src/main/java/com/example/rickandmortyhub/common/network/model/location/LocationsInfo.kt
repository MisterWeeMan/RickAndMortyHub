package com.example.rickandmortyhub.common.network.model.location


import com.example.rickandmortyhub.common.model.Location
import com.example.rickandmortyhub.common.network.model.Info
import com.google.gson.annotations.SerializedName

data class LocationsInfo(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val locations: List<Location>
)