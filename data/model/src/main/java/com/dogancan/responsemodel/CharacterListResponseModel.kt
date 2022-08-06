package com.dogancan.responsemodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * @author dogancankilic
 * Created on 30.07.2022
 */
data class CharacterListResponseModel(
    val characterList: Flow<PagingData<ResultsItem>>
)