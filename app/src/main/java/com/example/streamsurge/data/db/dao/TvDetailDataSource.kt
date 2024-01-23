package com.example.streamsurge.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.streamsurge.model.DiscoverResponse
import com.example.streamsurge.model.TvSeriesResponse

@Dao
interface TvDetailDataSource {

    @Query("SELECT * FROM TvSeriesDetails where id = :id")
    suspend fun getTvDetailsData(id: Int): TvSeriesResponse?

    @Upsert
    suspend fun insertTvDetailData(item: TvSeriesResponse)

    @Query("DELETE FROM TvSeriesDetails")
    suspend fun deleteAllTvDetail()
}