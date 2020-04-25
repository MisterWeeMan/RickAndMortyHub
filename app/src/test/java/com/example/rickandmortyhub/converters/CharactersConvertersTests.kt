package com.example.rickandmortyhub.converters

import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.network.model.character.CharacterLocation
import com.example.rickandmortyhub.common.network.model.character.Origin
import com.example.rickandmortyhub.common.utils.converters.CharactersConverter
import org.junit.Assert.assertEquals
import org.junit.Test

class CharactersConvertersTests {

    val charactersConverter = CharactersConverter()

    @Test
    fun `charactersConverter converts correctly Character to CharacterDb`() {
        // === Setup ===
        val character = Character(
            1, "Rick Sanchez", "Alive", "Human", "", "Male",
            Origin("Earth", "https://rickandmortyapi.com/api/location/1"),
            CharacterLocation("Earth", "https://rickandmortyapi.com/api/location/20"),
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            listOf("https://rickandmortyapi.com/api/episode/1"),
            "https://rickandmortyapi.com/api/character/1",
            "2017-11-04T18:48:46.250Z"
        )
        val expectedCharacter = CharacterDb(
            1, "Rick Sanchez", "Alive", "Human", "", "Male",
            "Earth", "https://rickandmortyapi.com/api/location/1",
            "Earth", "https://rickandmortyapi.com/api/location/20",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            listOf("https://rickandmortyapi.com/api/episode/1"),
            "https://rickandmortyapi.com/api/character/1",
            "2017-11-04T18:48:46.250Z"
        )

        // === Call ===
        val result = charactersConverter(character)

        // === Assertions ===
        assertEquals(expectedCharacter, result)
    }

    @Test
    fun `charactersConverter converts correctly CharacterDb to Character`() {
        // === Setup ===
        val characterDb = CharacterDb(
            1, "Rick Sanchez", "Alive", "Human", "", "Male",
            "Earth", "https://rickandmortyapi.com/api/location/1",
            "Earth", "https://rickandmortyapi.com/api/location/20",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            listOf("https://rickandmortyapi.com/api/episode/1"),
            "https://rickandmortyapi.com/api/character/1",
            "2017-11-04T18:48:46.250Z"
        )
        val expectedCharacter = Character(
            1, "Rick Sanchez", "Alive", "Human", "", "Male",
            Origin("Earth", "https://rickandmortyapi.com/api/location/1"),
            CharacterLocation("Earth", "https://rickandmortyapi.com/api/location/20"),
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            listOf("https://rickandmortyapi.com/api/episode/1"),
            "https://rickandmortyapi.com/api/character/1",
            "2017-11-04T18:48:46.250Z"
        )

        // === Call ===
        val result = charactersConverter(characterDb)

        // === Assertions ===
        assertEquals(expectedCharacter, result)
    }
}