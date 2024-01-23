package com.example.streamsurge.pagingSource

interface Paginator<Key,Item> {
    suspend fun loadNextItems()
    fun reset()
}