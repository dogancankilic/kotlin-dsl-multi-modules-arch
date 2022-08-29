package com.dogancan.domain.characterdetail

import com.dogancan.repository.character.CharacterRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
class CharacterDetailUseCase @Inject constructor(
    private val dataSource: CharacterRepository,
    private val characterDetailMapper: CharacterDetailMapper

) : ICharacterDetailUseCase {
    override suspend fun invoke(id: Int) = dataSource.getCharacter(id).map { response ->
        response.map {
            characterDetailMapper.map(it)
        }
    }
}
