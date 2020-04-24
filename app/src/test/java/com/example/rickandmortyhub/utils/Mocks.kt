package com.example.rickandmortyhub.utils

import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.network.model.character.CharacterLocation
import com.example.rickandmortyhub.common.network.model.character.Origin
import com.example.rickandmortyhub.common.network.model.episode.Episode
import com.example.rickandmortyhub.common.network.model.location.Location

val characterMock = Character(
    id = 1,
    name = "Rick Sanchez",
    status = "Alive",
    species = "Human",
    type = "",
    gender = "Male",
    origin = Origin(
        name = "Earth",
        url = "https://rickandmortyapi.com/api/location/1"
    ),
    characterLocation = CharacterLocation(
        name = "Earth",
        url = "https://rickandmortyapi.com/api/location/20"
    ),
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    episode = listOf(
        "https://rickandmortyapi.com/api/episode/1",
        "https://rickandmortyapi.com/api/episode/2",
        "https://rickandmortyapi.com/api/episode/3",
        "https://rickandmortyapi.com/api/episode/4"
    ),
    url = "https://rickandmortyapi.com/api/character/1",
    created = "2017-11-04T18:48:46.250Z"
)

val characterDbMock = CharacterDb(
    id = 1,
    name = "Rick Sanchez",
    status = "Alive",
    species = "Human",
    type = "",
    gender = "Male",
    nameOriginLocation = "Earth",
    urlOriginLocation = "https://rickandmortyapi.com/api/location/1",
    nameLocation = "Earth",
    urlLocation = "https://rickandmortyapi.com/api/location/20",
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    episode = listOf(
        "https://rickandmortyapi.com/api/episode/1",
        "https://rickandmortyapi.com/api/episode/2",
        "https://rickandmortyapi.com/api/episode/3",
        "https://rickandmortyapi.com/api/episode/4"
    ),
    url = "https://rickandmortyapi.com/api/character/1",
    created = "2017-11-04T18:48:46.250Z"
)

val episodeMock = Episode(
    id = 10,
    name = "Close Rick-counters of the Rick Kind",
    airDate = "April 7, 2014",
    episode = "S01E10",
    characters = listOf(
        "https://rickandmortyapi.com/api/character/1",
        "https://rickandmortyapi.com/api/character/2",
        "https://rickandmortyapi.com/api/character/5",
        "https://rickandmortyapi.com/api/character/8",
        "https://rickandmortyapi.com/api/character/23"
    ),
    url = "https://rickandmortyapi.com/api/episode/10",
    created = "2017-11-10T12:56:34.747Z"
)

val locationMock = Location(
    id = 3,
    name = "Citadel of Ricks",
    type = "Space station",
    dimension = "unknown",
    residents = listOf(
        "https://rickandmortyapi.com/api/character/8",
        "https://rickandmortyapi.com/api/character/14",
        "https://rickandmortyapi.com/api/character/24",
        "https://rickandmortyapi.com/api/character/25",
        "https://rickandmortyapi.com/api/character/83",
        "https://rickandmortyapi.com/api/character/100"
    ),
    url = "https://rickandmortyapi.com/api/location/3",
    created = "2017-11-10T13:08:13.191Z"
)