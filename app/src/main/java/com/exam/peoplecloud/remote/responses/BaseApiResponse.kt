package com.exam.peoplecloud.remote.responses


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseApiResponse {
    suspend inline fun <T> safeApiCall(
        crossinline body: suspend () -> T
    ): ResponseResult<T> {
        return try {
            val users = withContext(Dispatchers.IO) { body() }
            ResponseResult.Success(users)
        } catch (e: Exception) {
            ResponseResult.Failure(e)
        }
    }
}