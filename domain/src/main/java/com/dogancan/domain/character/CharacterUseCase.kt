package com.dogancan.domain.character

import androidx.paging.PagingData
import androidx.paging.map
import com.dogancan.remote.di.IoDispatcher
import com.dogancan.repository.character.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 3.06.2022
 */
class CharacterUseCase @Inject constructor(
    private val dataSource: CharacterRepository,
    @IoDispatcher private val ioScope: CoroutineDispatcher
) {

    fun getCharacters(): Flow<PagingData<CharacterUiModel>> {
        return dataSource
            .getCharacters().map {
                it.map {
                    CharacterUiModel(it.name)
                }
            }.flowOn(ioScope)
    }
}
