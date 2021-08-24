package com.yasir.sculpture.arena.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    call: suspend () -> T
): SculptureArenaResponse<T> = withContext(Dispatchers.IO) {
    try {
        SculptureArenaResponse.Success(call.invoke())
    } catch (exception: Exception) {
        SculptureArenaResponse.Error(exception, exception.message)
    }
}

sealed class SculptureArenaResponse<out R> {
    data class Success<out T>(val data: T) : SculptureArenaResponse<T>()
    data class Error(val exception: Exception, val message: String? = null) :
        SculptureArenaResponse<Nothing>()

}

val SculptureArenaResponse<*>.succeeded
    get() = this is SculptureArenaResponse.Success<*>

inline fun <T> SculptureArenaResponse<T>.onSuccess(
    crossinline callback: (value: T) -> Unit
): SculptureArenaResponse<T> {
    if (succeeded) {
        callback((this as SculptureArenaResponse.Success<T>).data)
    }
    return this
}

inline fun <T> SculptureArenaResponse<T>.onError(
    crossinline callback: (value: SculptureArenaResponse.Error) -> Unit
): SculptureArenaResponse<T> {
    if (!succeeded) {
        callback(this as SculptureArenaResponse.Error)
    }
    return this
}

