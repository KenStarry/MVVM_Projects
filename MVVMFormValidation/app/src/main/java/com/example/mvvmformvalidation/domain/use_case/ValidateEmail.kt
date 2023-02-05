package com.example.mvvmformvalidation.domain.use_case

import android.util.Patterns
import com.example.mvvmformvalidation.domain.Constants
import com.example.mvvmformvalidation.domain.model.ValidationResult

class ValidateEmail {

    fun execute(
        email: String
    ): ValidationResult {

        if (email.isBlank()) {
            return ValidationResult.Error(Constants.BLANK_EMAIL_ERROR)
        }

        //  check if email is a valid email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult.Error(Constants.INVALID_EMAIL_ERROR)
        }

        return ValidationResult.Success
    }
}