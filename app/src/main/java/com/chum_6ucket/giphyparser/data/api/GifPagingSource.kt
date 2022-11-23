package com.chum_6ucket.giphyparser.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chum_6ucket.giphyparser.data.models.DataItem

class GifPagingSource(
    private val service: NetworkService,
    private val query: String
) : PagingSource<Int, DataItem>() {

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response =
                if (query.isNullOrBlank())
                    service.getTrending(currentLoadingPageKey)
                else
                    service.getSearchedGif(query, params.loadSize, currentLoadingPageKey)

            val gifData = response.data
            val prevKey = if (currentLoadingPageKey == 0) null else currentLoadingPageKey - 25

            LoadResult.Page(
                data = gifData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}