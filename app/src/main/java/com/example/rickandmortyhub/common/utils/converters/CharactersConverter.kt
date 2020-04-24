package com.example.rickandmortyhub.common.utils.converters

import com.example.rickandmortyhub.common.database.model.CharacterDb
import com.example.rickandmortyhub.common.network.model.character.Character
import com.example.rickandmortyhub.common.network.model.character.CharacterLocation
import com.example.rickandmortyhub.common.network.model.character.Origin
import javax.inject.Inject

class CharactersConverter @Inject constructor() {

    operator fun invoke(characterDb: CharacterDb): Character {
        return Character(
            id = characterDb.id,
            name = characterDb.name,
            status = characterDb.status,
            species = characterDb.species,
            type = characterDb.type,
            gender = characterDb.gender,
            origin = Origin(
                name = characterDb.nameOriginLocation,
                url = characterDb.urlOriginLocation
            ),
            characterLocation = CharacterLocation(
                name = characterDb.nameLocation,
                url = characterDb.urlLocation
            ),
            image = characterDb.image,
            episode = characterDb.episode,
            url = characterDb.url,
            created = characterDb.created
        )
    }

    operator fun invoke(character: Character): CharacterDb {
        return CharacterDb(
            id = character.id,
            name = character.name,
            status = character.status,
            species = character.species,
            type = character.type,
            gender = character.gender,
            nameOriginLocation = character.origin.name,
            urlOriginLocation = character.origin.url,
            nameLocation = character.characterLocation.name,
            urlLocation = character.characterLocation.url,
            image = character.image,
            episode = character.episode,
            url = character.url,
            created = character.created
        )
    }
}