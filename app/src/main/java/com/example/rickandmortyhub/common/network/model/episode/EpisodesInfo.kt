package com.example.rickandmortyhub.common.network.model.episode


import com.example.rickandmortyhub.common.model.Episode
import com.example.rickandmortyhub.common.network.model.Info
import com.google.gson.annotations.SerializedName

data class EpisodesInfo(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val episodes: List<Episode>
)