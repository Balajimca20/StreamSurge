package com.example.streamsurge.repository

import android.content.Context
import com.example.streamsurge.data.db.dao.LocalDataSource
import com.example.streamsurge.mapper.discoverDataMapper
import com.example.streamsurge.mapper.tvSeriesDataMapper
import com.example.streamsurge.model.DiscoverResponse
import com.example.streamsurge.model.TvSeriesResponse
import com.example.streamsurge.network.ServiceApi
import com.example.streamsurge.network.handleResponse


class StreamRepository(
    private val serviceApi: ServiceApi,
    private val context: Context,
    private val localDataSource: LocalDataSource,
) {
    suspend fun getDiscover(page: Int): Result<DiscoverResponse> {
        val handleResponse = handleResponse {
            serviceApi.getDiscover(
                adult = false,
                airDates = false,
                language = "en-US",
                page = page,
                sortBy = "popularity.desc"
            )
        }
        val response = discoverDataMapper(handleResponse, context, localDataSource)
        return Result.success(
            DiscoverResponse(
                statusCode = response?.statusCode,
                status = response?.status,
                message = response?.message,
                page = response?.page ?: 0,
                totalPages = response?.totalPages ?: 0,
                totalResults = response?.totalResults ?: 0,
                tvShowItem = response?.tvShowItem ?: mutableListOf(),
            )
        )

    }

    suspend fun getTvDetail(tvId: String): TvSeriesResponse? {
        val handleResponse = handleResponse {
            serviceApi.getTvDetail(
                tvId = tvId,
                language = "en-US",
            )
        }
        return tvSeriesDataMapper(handleResponse, context, localDataSource)
    }

}