package com.example.rickandmortyhub.common

const val BASE_URL = "https://rickandmortyapi.com/api/"

const val CHARACTER_ENDPOINT = "character"
const val SINGLE_CHARACTER_ENDPOINT = "character/{characterId}"
const val LOCATION_ENDPOINT = "location"
const val SINGLE_LOCATION_ENDPOINT = "location/{locationId}"
const val EPISODE_ENDPOINT = "episode"
const val SINGLE_EPISODE_ENDPOINT = "episode/{episodeId}"

const val DATABASE_NAME = "RickMortyDatabase"

const val EXPIRING_DATA_PREFERENCES = "expiring_data_preferences"

const val LAST_DOWNLOAD_KEY = "last_download"

const val EXPIRING_DOWNLOAD_MILLIS: Long = 172_800_000L // 48 hours