package com.example.streamsurge.ui.detail

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun TvSeriesDetailRouter(
    viewModel: TvSeriesDetailViewModel,
    activity: Activity
) {
    val uiState by viewModel.uiState.collectAsState()
    TvSeriesDetailScreen(
        isLoading= uiState.isLoading,
        onBackPress={
            activity.finish()
        },
        tvSeriesDetailItem= uiState.tvSeriesDetailItem
    )
}