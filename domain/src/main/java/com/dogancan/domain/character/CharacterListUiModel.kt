package com.dogancan.domain.character

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * @author dogancankilic
 * Created on 30.07.2022
 */
data class CharacterListUiModel(
    val characterList: Flow<PagingData<CharacterUiModel>>
)
