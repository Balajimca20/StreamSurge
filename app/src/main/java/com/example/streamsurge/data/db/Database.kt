package com.example.streamsurge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.streamsurge.data.db.dao.LocalDataSource
import com.example.streamsurge.data.db.dao.TvDetailDataSource
import com.example.streamsurge.data.entity.Converters
import com.example.streamsurge.model.DiscoverResponse
import com.example.streamsurge.model.TvSeriesResponse

@Database(entities = [DiscoverResponse::class/*,TvSeriesResponse::class*/], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun localDao(): LocalDataSource
   /* abstract fun tvDetailDao(): TvDetailDataSource*/
}