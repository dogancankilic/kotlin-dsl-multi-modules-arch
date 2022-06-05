package com.dogancan.remote.character

import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
class CharacterDetailDataSource @Inject constructor(
    private val characterService: CharacterService
) {
    suspend fun getCharacter(id: Int) = characterService.getCharacter(id)
}
