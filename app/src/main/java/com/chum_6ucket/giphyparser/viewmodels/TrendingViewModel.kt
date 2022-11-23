package com.chum_6ucket.giphyparser.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chum_6ucket.giphyparser.data.GifTrendingRepository
import com.chum_6ucket.giphyparser.data.models.DataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val repository: GifTrendingRepository
) : ViewModel() {

    private var _searchText = MutableLiveData<String>()
    val searchText: LiveData<String>
        get() = _searchText

    fun onSearch(query: String) {
        _searchText.value = query
    }

    private var currentGiphyResult: Flow<PagingData<DataItem>>? = null

    fun fetchGifs(query: String = ""): Flow<PagingData<DataItem>> {
        val newResult: Flow<PagingData<DataItem>> =
            repository.getGifStream(query).cachedIn(viewModelScope)
        currentGiphyResult = newResult
        return newResult
    }
}