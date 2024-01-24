package com.example.streamsurge.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.streamsurge.ui.detail.TvSeriesDetailsActivity
import com.example.streamsurge.utils.Constants

@Composable
fun DashboardRouter(
    viewModel: DashboardViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    DashboardScreen(
        isLoading= uiState.isLoading,
        isPaginating = uiState.isPaginating,
        isEndReached = uiState.isEndReached,
        getTvShowItems = { viewModel.getTvShowItems() },
        searchValue = viewModel.searchValue,
        onSearchValueChanged={query->viewModel.searchList(query)},
        goToDetailActivity={
            val bundle = Bundle()
            bundle.putString(Constants.KEY_IS_TV_ID, it.id.toString())
            val intent = Intent(context, TvSeriesDetailsActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        },
        tvShowItems=uiState.tvShowItem,
        onRefreshing = { viewModel.onRefreshing(it) },
        isRefreshing = viewModel.isRefreshing,
    )
}