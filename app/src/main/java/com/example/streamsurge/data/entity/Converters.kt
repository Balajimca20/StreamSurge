package com.example.streamsurge.data.entity

import androidx.room.TypeConverter
import com.example.streamsurge.model.CreatedBy
import com.example.streamsurge.model.Genre
import com.example.streamsurge.model.LastEpisodeToAir
import com.example.streamsurge.model.Network
import com.example.streamsurge.model.NextEpisodeToAir
import com.example.streamsurge.model.ProductionCompany
import com.example.streamsurge.model.ProductionCountry
import com.example.streamsurge.model.Season
import com.example.streamsurge.model.SpokenLanguage
import com.example.streamsurge.model.TvShowItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    /**
     * Convert a json to a TvShowItem model
     */
    @TypeConverter
    fun toString(program: List<TvShowItem>?): String {
        return Gson().toJson(program)
    }
    @TypeConverter
    fun toTvShowItem(jsonString: String): List<TvShowItem> {
        val nameType = object : TypeToken<List<TvShowItem>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    /**
     * Convert a json to a CreatedBy model
     *//*
    @TypeConverter
    fun toString(program: List<CreatedBy>): String {
        return Gson().toJson(program)
    }
    @TypeConverter
    fun toCreatedBy(jsonString: String): List<CreatedBy> {
        val nameType = object : TypeToken<List<CreatedBy>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    *//**
     * Convert a json to a Any model
     *//*
    @TypeConverter
    fun toString(program: List<Any>?): String {
        return Gson().toJson(program)
    }
    @TypeConverter
    fun toAny(jsonString: String): List<Any> {
        val nameType = object : TypeToken<List<Any>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    *//**
     * Convert a json to a Genre model
     *//*
    @TypeConverter
    fun toString(item: List<Genre>?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toGenre(jsonString: String): List<Genre> {
        val nameType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }
    *//**
     * Convert a json to a String model
     *//*
    @TypeConverter
    fun toString(item: List<String>?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toListString(jsonString: String): List<String> {
        val nameType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    @TypeConverter
    fun toString(item: List<Network>?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toNetwork(jsonString: String): List<Network> {
        val nameType = object : TypeToken<List<Network>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    @TypeConverter
    fun toString(item: List<ProductionCompany>?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toProductionCompany(jsonString: String): List<ProductionCompany> {
        val nameType = object : TypeToken<List<ProductionCompany>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    @TypeConverter
    fun toString(item: List<ProductionCountry>?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toProductionCountry(jsonString: String): List<ProductionCountry> {
        val nameType = object : TypeToken<List<ProductionCountry>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    @TypeConverter
    fun toString(item: List<Season>?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toSeason(jsonString: String): List<Season> {
        val nameType = object : TypeToken<List<Season>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }

    @TypeConverter
    fun toString(item: List<SpokenLanguage>): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toSpokenLanguage(jsonString: String): List<SpokenLanguage> {
        val nameType = object : TypeToken<List<SpokenLanguage>>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }
    *//**
     * Convert a json to a LastEpisodeToAir model
     *//*
    @TypeConverter
    fun toString(item: LastEpisodeToAir?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toLastEpisodeToAir(jsonString: String): LastEpisodeToAir {
        val nameType = object : TypeToken<LastEpisodeToAir>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }
    *//**
     * Convert a json to a LastEpisodeToAir model
     *//*
    @TypeConverter
    fun toString(item: NextEpisodeToAir?): String {
        return Gson().toJson(item)
    }
    @TypeConverter
    fun toNextEpisodeToAir(jsonString: String): NextEpisodeToAir {
        val nameType = object : TypeToken<NextEpisodeToAir>() {}.type
        return Gson().fromJson(jsonString, nameType)
    }*/

}