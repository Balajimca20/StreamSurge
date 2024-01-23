package com.example.streamsurge.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun <T> T.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.toGson(): T {
    if (startsWith('[')) {
        return Gson().fromJson<T>(this, object : TypeToken<T>() {}.type)
    }
    return Gson().fromJson(this.toString(), T::class.java)
}

fun <R> CoroutineScope.executeAsyncTask(
    onPreExecute: () -> Unit,
    doInBackground: () -> R,
    onPostExecute: (R) -> Unit
) = launch {
    onPreExecute()
    val result =
        withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
            doInBackground()
        }
    onPostExecute(result)
}