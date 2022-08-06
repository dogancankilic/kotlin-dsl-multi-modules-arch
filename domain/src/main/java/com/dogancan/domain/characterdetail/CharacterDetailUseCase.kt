package com.dogancan.domain.characterdetail

import com.dogancan.repository.character.CharacterRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.map

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
class CharacterDetailUseCase @Inject constructor(
    private val dataSource: CharacterRepository,
    private val characterDetailMapper: CharacterDetailMapper

) {
    suspend fun invoke(id: Int) = dataSource.getUsers(id).map { response ->
        response.map {
            characterDetailMapper.map(it)
        }
    }
}
