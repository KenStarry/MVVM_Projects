package com.example.mvvmformvalidation.domain.use_case

data class ValidateUseCases(
    val validateEmail: ValidateEmail = ValidateEmail(),
    val validatePassword: ValidatePassword = ValidatePassword(),
    val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    val validateTerms: ValidateTerms = ValidateTerms()
)
