package com.dogancan.repository.character

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dogancan.remote.character.CharacterDataSource
import com.dogancan.remote.character.CharacterDetailDataSource
import com.dogancan.remote.character.CharacterService
import com.dogancan.repository.base.BaseRepository
import com.dogancan.responsemodel.ResultsItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 3.06.2022
 */
class CharacterRepository @Inject constructor(
    private val service: CharacterService,
    private val characterDetailDataSource: CharacterDetailDataSource
) : BaseRepository() {

    fun getCharacters(): Flow<PagingData<ResultsItem>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                CharacterDataSource(service)
            }, initialKey = 1
        ).flow
    }

    suspend fun getUsers(id: Int) = invoke {
        characterDetailDataSource.getCharacter(id)
    }
}
