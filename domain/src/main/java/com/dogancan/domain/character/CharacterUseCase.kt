package com.dogancan.domain.character

import androidx.paging.PagingData
import androidx.paging.map
import com.dogancan.repository.character.CharacterRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @author dogancankilic
 * Created at 3.06.2022
 */
class CharacterUseCase @Inject constructor(
    private val dataSource: CharacterRepository,
) {

    fun getCharacters(): Flow<PagingData<CharacterUiModel>> {
        return dataSource
            .getCharacters().map {
                it.map {
                    CharacterUiModel(it.id, it.name)
                }
            }
    }
}
