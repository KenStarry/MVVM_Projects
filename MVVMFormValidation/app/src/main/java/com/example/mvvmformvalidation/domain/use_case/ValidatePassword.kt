package com.example.mvvmformvalidation.domain.use_case

import android.util.Patterns
import com.example.mvvmformvalidation.domain.Constants
import com.example.mvvmformvalidation.domain.model.ValidationResult

class ValidatePassword {

    fun execute(
        password: String
    ): ValidationResult {

        if (password.length < Constants.PASSWORD_LENGTH) {
            return ValidationResult(
                successful = false,
                errorMessage = Constants.PASSWORD_LENGTH_ERROR
            )
        }

        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }

        //  check if password contains letters and digits
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = Constants.PASSWORD_LETTERS_ERROR
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}