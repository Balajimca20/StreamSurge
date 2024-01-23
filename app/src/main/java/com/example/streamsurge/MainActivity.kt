package com.example.streamsurge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.streamsurge.ui.dashboard.DashboardRouter
import com.example.streamsurge.ui.dashboard.DashboardViewModel
import com.example.streamsurge.ui.theme.StreamSurgeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamSurgeTheme {
                DashboardRouter(viewModel)
            }
        }
    }
}
