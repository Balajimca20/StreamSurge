package com.example.streamsurge.network


import com.example.streamsurge.model.DiscoverResponse
import com.example.streamsurge.model.TvSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ServiceApi {

    @GET("3/discover/tv")
    suspend fun getDiscover(
        @Query("include_adult") adult:Boolean?,
        @Query("include_null_first_air_dates") airDates:Boolean?,
        @Query("language") language:String?,
        @Query("page") page:Int?,
        @Query("sort_by") sortBy:String?,
    ) : Response<DiscoverResponse?>

    @GET("3/tv/{tvId}")
    suspend fun getTvDetail(
        @Path("tvId") tvId:String?,
        @Query("language") language:String?,
    ) : Response<TvSeriesResponse?>

}