package com.dogancan.domain.character

import androidx.paging.map
import com.dogancan.repository.character.CharacterRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.map

/**
 * @author dogancankilic
 * Created at 3.06.2022
 */
class CharacterUseCase @Inject constructor(
    private val dataSource: CharacterRepository,
) {

    fun getCharacters(): CharacterListUiModel {
        return CharacterListUiModel(dataSource.getCharacters().characterList.map {
            it.map { item ->
                CharacterUiModel(item.id, item.name)
            }
        })

    }
}
