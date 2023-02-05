package com.example.mvvmformvalidation.domain.model

sealed class ValidationResult(
//    val successful: Boolean,
//    val errorMessage: String? = null
) {

    object Success : ValidationResult()

    data class Error(val message: String?) : ValidationResult()
}
