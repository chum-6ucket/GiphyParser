package com.chum_6ucket.giphyparser.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chum_6ucket.giphyparser.data.api.GifPagingSource
import com.chum_6ucket.giphyparser.data.api.NetworkService
import com.chum_6ucket.giphyparser.data.models.DataItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GifTrendingRepository @Inject constructor(private val service: NetworkService) {

    fun getGifStream(query: String): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { GifPagingSource(service, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 25
    }

}