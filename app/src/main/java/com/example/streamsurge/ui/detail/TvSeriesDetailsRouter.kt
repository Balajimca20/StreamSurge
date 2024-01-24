package com.example.streamsurge.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.streamsurge.ui.dashboard.DashboardScreen

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