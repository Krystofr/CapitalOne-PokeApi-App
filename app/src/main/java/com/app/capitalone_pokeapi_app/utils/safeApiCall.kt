package com.app.capitalone_pokeapi_app.utils

import android.util.Log
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.io.IOException
import kotlin.coroutines.cancellation.CancellationException

inline fun <T : Any> safeApiCall(
    crossinline apiCall: suspend () -> T
): Flow<Resource<T>> = flow {
    emit(Resource.Loading)
    val response = apiCall()
    emit(Resource.Success(response))
}.catch { e ->
    if (e is CancellationException) throw e

    val message = when (e) {
        is ClientRequestException -> {
            val errorBody = e.response.bodyAsText()
            Log.e("API_ERROR", "Client error: ${e.response.status} - $errorBody", e)
            "Client error: ${e.response.status}"
        }
        is ServerResponseException -> {
            val errorBody = e.response.bodyAsText()
            Log.e("API_ERROR", "Server error: ${e.response.status} - $errorBody", e)
            "Server error: ${e.response.status}"
        }
        is IOException -> {
            Log.e("API_ERROR", "Network error: ${e.message}", e)
            "Network error: ${e.message}"
        }
        else -> {
            Log.e("API_ERROR", "Unexpected error: ${e.message}", e)
            "Unexpected error: ${e.message}"
        }
    }

    emit(Resource.Error(message))
}.flowOn(Dispatchers.IO)
