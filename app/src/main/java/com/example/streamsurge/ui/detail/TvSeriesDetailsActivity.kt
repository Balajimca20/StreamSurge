package com.example.streamsurge.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.streamsurge.ui.detail.ui.theme.StreamSurgeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvSeriesDetailsActivity : ComponentActivity() {

    private val viewModel: TvSeriesDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getIntent(intent)
        setContent {
            StreamSurgeTheme {
                TvSeriesDetailRouter(viewModel = viewModel,this@TvSeriesDetailsActivity)
            }
        }
    }
}
