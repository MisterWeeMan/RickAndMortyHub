package com.example.rickandmortyhub.common.network.model.character


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)