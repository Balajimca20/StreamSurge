package com.example.streamsurge.ui.dashboard


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamsurge.model.TvShowItem
import com.example.streamsurge.pagingSource.PagingSource
import com.example.streamsurge.repository.StreamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: StreamRepository,
) : ViewModel() {

    private var cacheTvShowItem: MutableList<TvShowItem> = mutableListOf()

    private var currentPage: Int = 1
    private var totalPages: Int = 1
    var searchValue by mutableStateOf("")
        private set

    private val viewModelState = MutableStateFlow(DashboardViewModelState(isLoading = true))
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    private val pagination = PagingSource(
        initialKey = 1,
        onLoadUpdated = { loadState ->
            viewModelState.update { it.copy(isPaginating = if (!viewModelState.value.isLoading) loadState else false) }
        },
        onRequest = { nextPage ->
            repository.getDiscover(
                nextPage,
            )
        },
        getNextKey = {
            currentPage + 1
        },
        onError = { viewModelState.update { it.copy(isLoading = false) } },
        onSuccess = { items, newKey ->
            currentPage = newKey
            totalPages = items.totalPages
            cacheTvShowItem = items.tvShowItem?.map { showItem ->
                TvShowItem(
                    id = showItem.id,
                    backdropPath = showItem.backdropPath ?: "",
                    firstAirDate = showItem.firstAirDate,
                    genreIds = showItem.genreIds,
                    name = showItem.name,
                    originCountry = showItem.originCountry,
                    originalLanguage = showItem.originalLanguage,
                    originalName = showItem.originalName,
                    overview = showItem.overview,
                    popularity = showItem.popularity,
                    posterPath = showItem.posterPath,
                    voteAverage = showItem.voteAverage,
                    voteCount = showItem.voteCount,
                )
            } as MutableList<TvShowItem>
            viewModelState.update {
                it.copy(
                    lastUpdate = System.currentTimeMillis(),
                    isLoading = false,
                    isEndReached = items.tvShowItem?.isEmpty() ?: false,
                    tvShowItem = cacheTvShowItem,
                )
            }
        }
    )

    init {
        getTvShowItems()
    }

    fun getTvShowItems() {
        viewModelScope.launch {
            if (currentPage <= totalPages) {
                viewModelState.update { it.copy(isPaginating = !viewModelState.value.isLoading) }
                pagination.loadNextItems()
            }
        }
    }

    private fun onResetAnnouncements() {
        currentPage = 1
        totalPages = 1
        cacheTvShowItem.clear()
        pagination.reset()
        getTvShowItems()
    }

    fun searchList(query: String) {
        searchValue = query
        viewModelScope.launch {
            if (searchValue.isEmpty()) {
                viewModelState.update {
                    it.copy(tvShowItem = cacheTvShowItem)
                }
            } else {
                val results = cacheTvShowItem.filter {
                    it.name?.contains(
                        query,
                        ignoreCase = true
                    ) == true || it.overview?.contains(
                        query,
                        ignoreCase = true
                    ) == true
                }
                viewModelState.update {
                    it.copy(tvShowItem = results)
                }
            }
        }
    }


    data class DashboardViewModelState(
        val lastUpdate: Long = System.currentTimeMillis(),
        val isLoading: Boolean = false,
        val isPaginating: Boolean = false,
        var isEndReached: Boolean = false,
        val tvShowItem: List<TvShowItem> = mutableListOf(),

        ) {
        fun toUiState(): DashboardUiState {
            return DashboardUiState(
                isLoading = isLoading,
                isPaginating = isPaginating,
                lastUpdate = lastUpdate,
                tvShowItem = tvShowItem,
                isEndReached = isEndReached,
            )
        }
    }

    data class DashboardUiState(
        val isLoading: Boolean,
        val isPaginating: Boolean,
        val lastUpdate: Long,
        val tvShowItem: List<TvShowItem>,
        val isEndReached: Boolean,
    )
}