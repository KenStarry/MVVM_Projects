package com.example.ticky.feature_task_groups.domain.model

sealed class Response<out T> {

    //  loading state
    object Loading : Response<Nothing>()

    //  success state
    data class Success<out T>(val data: T) : Response<T>()

    //  failed state
    data class Failure(val e: Exception?) : Response<Nothing>()
}
