package com.example.streamsurge.ui.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.streamsurge.R
import com.example.streamsurge.extensions.toJson
import com.example.streamsurge.model.TvShowItem
import com.example.streamsurge.ui.dashboard.Toolbar
import com.example.streamsurge.utils.Constants


@Preview(showBackground = true)
@Composable
fun TvSeriesDetailScreenPreview() {
    TvSeriesDetailScreen(
        isLoading = false,
        searchValue = "",
        onSearchValueChanged = {},
        onBackPress = {},
        tvShowItems = arrayListOf()
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun TvSeriesDetailScreen(
    isLoading: Boolean,
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    onBackPress: () -> Unit,
    tvShowItems: List<TvShowItem>,
) {
    var clearDialog by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar(
                searchValue = searchValue,
                onSearchValueChanged = onSearchValueChanged,
                onDismissClearText = {
                    clearDialog = it
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                if (isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            color = Color.Red
                        )
                    }
                } else if (tvShowItems.isEmpty()) {
                    EmptyScreen()
                } else
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 128.dp),
                        content = {
                            items(tvShowItems) { data ->
                                SeriesItem(data)
                            }
                        }
                    )
            }

        }

    )
}

@Composable
fun SeriesItem(showItem: TvShowItem) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
            .shadow(
                elevation = 3.dp,
                spotColor = Color(0x33111827),
                ambientColor = Color(0x33111827)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE5E7EB),
                shape = RoundedCornerShape(size = 8.dp)
            ),
    ) {
        TvSeriesContent(showItem)
    }

}

@Composable
fun TvSeriesContent(showItem: TvShowItem) {
    AsyncImage(
        model = "${Constants.BASE_IMAGE_URL}${showItem.posterPath}",
        contentDescription = "backdrop_path"
    )
}

@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_announcement),
            contentDescription = "Announcement",
            tint = colorResource(id = R.color.inprogress_violet),
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = stringResource(id = R.string.no_data_found))

    }
}

fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp
): Modifier = composed {
    composed {
        val targetAlpha = if (state.isScrollInProgress) 1f else 0f
        val duration = if (state.isScrollInProgress) 150 else 500
        val alpha by animateFloatAsState(
            targetValue = targetAlpha,
            animationSpec = tween(durationMillis = duration)
        )
        drawWithContent {
            drawContent()
            val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
            val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f
            if (needDrawScrollbar && firstVisibleElementIndex != null) {
                val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
                val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
                val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight
                drawRoundRect(
                    color = Color.LightGray,
                    topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                    size = Size(width.toPx(), scrollbarHeight),
                    alpha = alpha,
                    cornerRadius = CornerRadius(x = 36.dp.toPx(), y = 36.dp.toPx())
                )
            }
        }
    }
}