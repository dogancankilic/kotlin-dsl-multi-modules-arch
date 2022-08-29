package com.dogancan.domain.character

import androidx.paging.map
import com.dogancan.repository.character.CharacterRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 3.06.2022
 */
class CharacterUseCase @Inject constructor(
    private val dataSource: CharacterRepository,
) : ICharacterUseCase {

    override fun getCharacters(): CharacterListUiModel {
        return CharacterListUiModel(
            dataSource.getCharacters().characterList.map {
                it.map { item ->
                    CharacterUiModel(item.id, item.name, item.image)
                }
            }
        )
    }
}
