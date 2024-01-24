package com.example.streamsurge.model

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Keep
@Immutable
@Entity(tableName = "Discover")
data class DiscoverResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "status_code")
    @SerializedName("status_code")
    val statusCode: Int?,

    @ColumnInfo(name = "status")
    @SerializedName("status")
    val status: Boolean?,

    @ColumnInfo(name = "message")
    @SerializedName("message")
    val message: String?,

    @ColumnInfo(name = "page")
    @SerializedName("page")
    val page: Int,

    @ColumnInfo(name = "results")
    @SerializedName("results")
    var tvShowItem: List<TvShowItem>?,

    @ColumnInfo(name = "total_pages")
    @SerializedName("total_pages")
    val totalPages: Int,

    @ColumnInfo(name = "total_results")
    @SerializedName("total_results")
    val totalResults: Int
)

data class TvShowItem(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("id")
    val id: Int?,
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: List<String>?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_name")
    val originalName: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
)
