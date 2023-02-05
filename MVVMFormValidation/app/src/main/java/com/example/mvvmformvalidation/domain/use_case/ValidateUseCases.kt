package com.example.mvvmformvalidation.domain.use_case

data class ValidateUseCases(
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword,
    val validateTerms: ValidateTerms
)
