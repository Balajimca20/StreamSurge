package com.example.streamsurge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "TvSeriesDetails")
data class TvSeriesResponse(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean? = false,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String?? = null,
    @ColumnInfo(name = "created_by")
    @SerializedName("created_by")
    val createdBy: List<CreatedBy>? = null,
    @ColumnInfo(name = "episode_run_time")
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Any>? = null,
    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @ColumnInfo(name = "homepage")
    @SerializedName("homepage")
    val homepage: String? = null,
    @ColumnInfo(name = "in_production")
    @SerializedName("in_production")
    val inProduction: Boolean? = null,
    @ColumnInfo(name = "languages")
    @SerializedName("languages")
    val languages: List<String>? = null,
    @ColumnInfo(name = "last_air_date")
    @SerializedName("last_air_date")
    val lastAirDate: String? = null,
    @ColumnInfo(name = "last_episode_to_air")
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir? = null,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String? = null,
    @ColumnInfo(name = "networks")
    @SerializedName("networks")
    val networks: List<Network>? = null,
    @ColumnInfo(name = "next_episode_to_air")
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAir? = null,
    @ColumnInfo(name = "number_of_episodes")
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    @ColumnInfo(name = "number_of_seasons")
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,
    @ColumnInfo(name = "origin_country")
    @SerializedName("origin_country")
    val originCountry: List<String>? = null,
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @ColumnInfo(name = "original_name")
    @SerializedName("original_name")
    val originalName: String? = null,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String? = null,
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double? = null,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @ColumnInfo(name = "production_companies")
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @ColumnInfo(name = "production_countries")
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @ColumnInfo(name = "seasons")
    @SerializedName("seasons")
    val seasons: List<Season>? = null,
    @ColumnInfo(name = "spoken_languages")
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    @ColumnInfo(name = "status")
    @SerializedName("status")
    val status: String? = null,
    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    val tagline: String? = null,
    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String? = null,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int? = null
)

data class CreatedBy(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: Any
)

data class Genre(
    val id: Int,
    val name: String
)

data class LastEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val episode_type: String,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Any,
    val season_number: Int,
    val show_id: Int,
    val still_path: Any,
    val vote_average: Int,
    val vote_count: Int
)

data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class NextEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val episode_type: String,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Any,
    val season_number: Int,
    val show_id: Int,
    val still_path: Any,
    val vote_average: Int,
    val vote_count: Int
)

data class ProductionCompany(
    val id: Int,
    val logo_path: Any,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Double
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)
