package com.dogancan.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dogancan.remote.character.CharacterDataSource
import com.dogancan.remote.character.CharacterDetailDataSource
import com.dogancan.remote.character.CharacterService
import com.dogancan.remote.di.IoDispatcher
import com.dogancan.repository.base.BaseRepository
import com.dogancan.responsemodel.CharacterListResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 3.06.2022
 */
class CharacterRepository @Inject constructor(
    private val service: CharacterService,
    private val characterDetailDataSource: CharacterDetailDataSource,
    @IoDispatcher private val ioScope: CoroutineDispatcher
) : BaseRepository() {

    fun getCharacters(): CharacterListResponseModel {
        return CharacterListResponseModel(
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                ),
                pagingSourceFactory = {
                    CharacterDataSource(service)
                }, initialKey = 1
            ).flow
        )
    }

    suspend fun getCharacter(id: Int) = invoke {
        characterDetailDataSource.getCharacter(id)
    }.flowOn(ioScope)
}
