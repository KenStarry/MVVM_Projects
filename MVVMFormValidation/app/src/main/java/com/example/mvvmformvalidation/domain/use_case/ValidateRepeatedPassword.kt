package com.example.mvvmformvalidation.domain.use_case

import android.util.Patterns
import com.example.mvvmformvalidation.domain.Constants
import com.example.mvvmformvalidation.domain.model.ValidationResult

class ValidateRepeatedPassword {

    fun execute(
        password: String,
        repeatedPassword: String
    ): ValidationResult {

        if (password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = Constants.PASSWORD_MATCH_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}