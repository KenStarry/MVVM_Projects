package com.example.mvvmformvalidation.domain.use_case

import android.util.Patterns
import com.example.mvvmformvalidation.domain.Constants
import com.example.mvvmformvalidation.domain.model.ValidationResult

class ValidateTerms {

    fun execute(
        acceptedTerms: Boolean
    ): ValidationResult {

        if (!acceptedTerms) {
            return ValidationResult.Error(Constants.TERMS_ERROR)
        }

        return ValidationResult.Success
    }
}