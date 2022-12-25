package com.example.books_app.feature_books.domain.model

sealed class Response<out T> {

    //  if in loading state
    object Loading: Response<Nothing>()

    //  onSuccess
    data class Success<out T>(
        val data: T
    ): Response<T>()

    //  onFailure
    data class Failure(
        val e: Exception?
    ): Response<Nothing>()

}
