package com.example.rickandmortyhub.network.model.character


import com.example.rickandmortyhub.network.model.Info
import com.google.gson.annotations.SerializedName

data class CharactersInfo(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val characters: List<Character>
)