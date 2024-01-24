package com.example.streamsurge.mapper


import android.content.Context
import android.util.Log
import com.example.streamsurge.data.db.dao.LocalDataSource
import com.example.streamsurge.extensions.toJson
import com.example.streamsurge.model.DiscoverResponse
import com.example.streamsurge.model.TvSeriesResponse
import com.example.streamsurge.network.Response

suspend fun discoverDataMapper(
    response: Response<DiscoverResponse?>,
    context: Context,
    localDataSource: LocalDataSource
): DiscoverResponse? {
    return when (response) {
        is Response.Failure -> {
            DiscoverResponse(
                statusCode = 500,
                status = false,
                message = response.error.msg.en,
                page = 0,
                tvShowItem = null,
                totalPages = 100,
                totalResults = 100
            )
        }
        is Response.NoNetwork -> {
            val localData = localDataSource.getAllData()
            return if (localData !=null && localData.tvShowItem?.isNotEmpty() == true) {
                localData
            } else
                DiscoverResponse(
                    statusCode = 500,
                    status = false,
                    message = context.getString(response.messageId),
                    page = 0,
                    tvShowItem = null,
                    totalPages = 100,
                    totalResults = 100
                )
        }

        is Response.Success -> {
            val remoteData = response.data?.tvShowItem
            val localData = localDataSource.getAllData()
            // Find missing items by comparing remote and local data
            val missingData = remoteData?.filterNot { remoteItem ->
                localData?.tvShowItem?.any { localItem -> localItem.id == remoteItem.id } == true
            }
            val data=localData?.tvShowItem as ArrayList
            if(missingData.isNullOrEmpty()) {
                missingData?.let { data.addAll(it) }
                response.data?.tvShowItem =  data
                response.data?.let { localDataSource.insertData(it) }
            }
            return response.data
        }
    }
}


suspend fun tvSeriesDataMapper(
    response: Response<TvSeriesResponse?>,
    context: Context,
    localDataSource: LocalDataSource
): TvSeriesResponse? {
    return when (response) {
        is Response.Failure -> {
            TvSeriesResponse()
        }
        is Response.NoNetwork -> {
            /*val localData = localDataSource.getAllData()
            return if (localData !=null && localData.tvShowItem?.isNotEmpty() == true) {
                localData
            } else*/
                TvSeriesResponse()
        }

        is Response.Success -> {
            /*val remoteData = response.data?.tvShowItem
            val localData = localDataSource.getAllData()
            // Find missing items by comparing remote and local data
            val missingData = remoteData?.filterNot { remoteItem ->
                localData?.tvShowItem?.any { localItem -> localItem.id == remoteItem.id } == true
            }
            if (missingData?.isNotEmpty() == true) {
                response.data.tvShowItem = missingData
                localDataSource.insertData(response.data)
            }*/
            return response.data
        }
    }
}
