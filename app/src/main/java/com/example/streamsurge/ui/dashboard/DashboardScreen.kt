package com.example.streamsurge.ui.dashboard

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.streamsurge.R
import com.example.streamsurge.model.TvShowItem
import com.example.streamsurge.utils.Constants
import com.example.streamsurge.utils.getDateWithMonth


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        isLoading = false,
        isPaginating = false,
        isEndReached = false,
        searchValue = "",
        onSearchValueChanged = {},
        getTvShowItems = {},
        goToDetailActivity = {},
        tvShowItems = arrayListOf(),
        onRefreshing = {},
        isRefreshing = false,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun DashboardScreen(
    isLoading: Boolean,
    isPaginating: Boolean,
    isEndReached: Boolean,
    getTvShowItems: () -> Unit,
    searchValue: String,
    onSearchValueChanged: (String) -> Unit,
    goToDetailActivity: (TvShowItem) -> Unit,
    tvShowItems: List<TvShowItem>,
    onRefreshing: (Boolean) -> Unit,
    isRefreshing: Boolean,
) {

    var clearDialog by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val layoutInfo = remember { derivedStateOf { listState.layoutInfo } }
    val isNeedPaginate =
        remember {
            derivedStateOf {
                layoutInfo.value.visibleItemsInfo.lastOrNull()?.index == layoutInfo.value.totalItemsCount - 2
            }
        }
    if (isNeedPaginate.value && !isEndReached && !isPaginating) {
        getTvShowItems()
    }
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
                    LazyColumn(
                        modifier = Modifier.simpleVerticalScrollbar(listState),
                        state = listState,
                        content = {
                            items(tvShowItems,
                                key = {it.id?:""}) { data ->
                                SeriesItem(
                                    data,
                                    onclickItem = {
                                        goToDetailActivity(data)
                                    })
                            }
                            item {
                                if (isPaginating) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .padding(vertical = 10.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        CircularProgressIndicator(
                                            color = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                    )
            }

        }

    )
}

@Composable
fun SeriesItem(
    showItem: TvShowItem,
    onclickItem: () -> Unit
) {
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
            )
            .clickable {
                onclickItem()
            },
    ) {
        TvSeriesContent(showItem)
    }

}

@Composable
fun TvSeriesContent(showItem: TvShowItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = "${Constants.BASE_IMAGE_URL}${showItem.posterPath}",
            contentDescription = "backdrop_path",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(96.dp)
                .width(96.dp),
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
        ) {
            Text(
                text = showItem.name ?: "",
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = getDateWithMonth(showItem.firstAirDate),
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }

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