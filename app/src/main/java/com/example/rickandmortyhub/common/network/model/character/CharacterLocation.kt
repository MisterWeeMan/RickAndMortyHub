package com.example.rickandmortyhub.common.network.model.character


import com.google.gson.annotations.SerializedName

data class CharacterLocation(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)