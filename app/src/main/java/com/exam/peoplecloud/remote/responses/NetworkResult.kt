package com.exam.peoplecloud.remote.responses


sealed class ResponseResult<out H> {
    data class Success<out T>(val data: T)
        : ResponseResult<T>()
    data class Failure(val error: Throwable)
        : ResponseResult<Nothing>()
}

