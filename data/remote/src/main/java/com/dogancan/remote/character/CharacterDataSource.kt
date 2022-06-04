package com.dogancan.remote.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dogancan.responsemodel.ResultsItem

/**
 * @author dogancankilic
 * Created at 3.06.2022
 */
class CharacterDataSource(private val apiService: CharacterService) :
    PagingSource<Int, ResultsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getCharacters(position).body()
            val endOfPaginationReached = response?.results?.isEmpty()
            if (response?.results?.isNotEmpty() == true) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (endOfPaginationReached == true) null else position + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
