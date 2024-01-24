package com.example.streamsurge.ui.detail


import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamsurge.model.TvSeriesResponse
import com.example.streamsurge.repository.StreamRepository
import com.example.streamsurge.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TvSeriesDetailViewModel(
    private val repository: StreamRepository,
) : ViewModel() {

    private var tvId = ""
    private val viewModelState = MutableStateFlow(TvSeriesDetailViewModelState(isLoading = true))
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    fun getIntent(intent: Intent?) {
        tvId = intent?.getStringExtra(Constants.KEY_IS_TV_ID).toString()
        getTvShowDetails()
    }

    private fun getTvShowDetails() {
        viewModelScope.launch {
            val response = repository.getTvDetail(tvId = tvId)
            viewModelState.update {
                it.copy(
                    lastUpdate = System.currentTimeMillis(),
                    isLoading = false,
                    tvSeriesDetailItem = response,
                )
            }
        }
    }

    data class TvSeriesDetailViewModelState(
        val lastUpdate: Long = System.currentTimeMillis(),
        val isLoading: Boolean = false,
        val tvSeriesDetailItem: TvSeriesResponse? = null,

        ) {
        fun toUiState(): TvSeriesDetailUiState {
            return TvSeriesDetailUiState(
                isLoading = isLoading,
                lastUpdate = lastUpdate,
                tvSeriesDetailItem = tvSeriesDetailItem,
            )
        }
    }

    data class TvSeriesDetailUiState(
        val isLoading: Boolean,
        val lastUpdate: Long,
        val tvSeriesDetailItem: TvSeriesResponse?,
    )
}