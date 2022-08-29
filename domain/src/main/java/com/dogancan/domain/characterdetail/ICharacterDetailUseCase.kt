package com.dogancan.domain.characterdetail

import com.dogancan.domain.character.CharacterUiModel
import kotlinx.coroutines.flow.Flow

/**
 * @author dogancankilic
 * Created on 7.08.2022
 */
interface ICharacterDetailUseCase {
    suspend fun invoke(id: Int): Flow<Result<CharacterUiModel>>
}
