package com.example.streamsurge.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.streamsurge.R
import com.example.streamsurge.model.TvSeriesResponse
import com.example.streamsurge.utils.Constants
import com.example.streamsurge.utils.getDateWithMonth


@Preview(showBackground = true)
@Composable
fun TvSeriesDetailScreenPreview() {
    TvSeriesDetailScreen(
        isLoading = false,
        onBackPress = {},
        tvSeriesDetailItem = null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvSeriesDetailScreen(
    isLoading: Boolean,
    onBackPress: () -> Unit,
    tvSeriesDetailItem: TvSeriesResponse?,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPress()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.black_light))
                    .padding(paddingValues)
                    .padding(16.dp)

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
                } else {
                    TvSeriesDetailItem(tvSeriesDetailItem)
                }

            }

        }

    )
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TvSeriesDetailItem(tvSeriesDetailItem: TvSeriesResponse?) {
    Spacer(modifier = Modifier.padding(4.dp))
    tvSeriesDetailItem?.seasons?.let { networks ->
        LazyColumn(content = {
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,

                        ) {
                        AsyncImage(
                            model = "${Constants.BASE_IMAGE_URL}${tvSeriesDetailItem.posterPath}",
                            contentDescription = null,
                            modifier = Modifier
                                .width(162.dp)
                                .height(162.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(0.5f),
                            text = tvSeriesDetailItem.name ?: "",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Text(
                            text = tvSeriesDetailItem.lastEpisodeToAir?.episode_type ?: "",
                            color = colorResource(id = R.color.white),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .background(
                                    color = colorResource(id = R.color.genres_txt),
                                    shape = RoundedCornerShape(6f)

                                )
                                .padding(horizontal = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Row {
                        Text(
                            text = "U/A",
                            color = colorResource(id = R.color.genres_txt),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = tvSeriesDetailItem.numberOfSeasons.toString(),
                            color = colorResource(id = R.color.genres_txt),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp),
                            text = tvSeriesDetailItem.status ?: "",
                            color = colorResource(id = R.color.genres_txt),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)

                        )
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = stringResource(id = R.string.sessions),
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = tvSeriesDetailItem.lastEpisodeToAir?.season_number.toString(),
                            color = colorResource(id = R.color.genres_txt),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .size(14.dp),
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color.White,
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = tvSeriesDetailItem.voteAverage.toString(),
                            color = colorResource(id = R.color.genres_txt),
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Divider()
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row {
                        Column {
                            Text(
                                text = stringResource(id = R.string.release_date),
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                            Text(
                                text = getDateWithMonth(tvSeriesDetailItem.lastAirDate),
                                color = colorResource(id = R.color.genres_txt),
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.genre),
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                            FlowRow {
                                tvSeriesDetailItem.genres?.forEach { genres ->
                                    Text(
                                        modifier = Modifier
                                            .padding(horizontal = 4.dp, vertical = 2.dp)
                                            .border(
                                                width = 2.dp,
                                                color = colorResource(id = R.color.genres_outer),
                                                shape = RoundedCornerShape(size = 16.dp)
                                            )
                                            .padding(horizontal = 8.dp, vertical = 2.dp),
                                        text = genres.name,
                                        color = colorResource(id = R.color.genres_txt),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Divider()
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = stringResource(id = R.string.overview),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = tvSeriesDetailItem.overview ?: "",
                        color = colorResource(id = R.color.genres_txt),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = stringResource(id = R.string.episode),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
            items(
                items = networks,
                key = { it.id }) { item ->
                Card(
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                        .fillMaxWidth()
                ) {
                    Row {
                        AsyncImage(
                            model = "${Constants.BASE_IMAGE_URL}${item.poster_path}",
                            error = painterResource(R.drawable.ic_announcement),
                            contentDescription = null,
                            modifier = Modifier
                                .width(64.dp)
                                .height(64.dp)
                        )
                        Column {
                            Text(
                                text = item.name,
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                            if (item.overview.isNotEmpty())
                                Text(
                                    text = item.overview,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 3
                                )
                            Text(
                                text = stringResource(
                                    id = R.string.episode_with_count,
                                    "${item.episode_count}"
                                ),
                                color = Color.Black,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        }

                    }

                }

            }
        })
    }
}
