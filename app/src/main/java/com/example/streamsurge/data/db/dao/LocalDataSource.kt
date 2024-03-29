package com.example.streamsurge.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.streamsurge.model.DiscoverResponse

@Dao
interface LocalDataSource {

    @Query("SELECT * FROM Discover")
    suspend fun getAllData(): DiscoverResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(discover: DiscoverResponse)

    @Query("DELETE FROM Discover")
    suspend fun deleteAll()
}