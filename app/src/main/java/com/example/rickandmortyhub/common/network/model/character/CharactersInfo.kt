package com.example.rickandmortyhub.common.network.model.character


import com.example.rickandmortyhub.common.network.model.Info
import com.google.gson.annotations.SerializedName

data class CharactersInfo(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val characters: List<Character>
)