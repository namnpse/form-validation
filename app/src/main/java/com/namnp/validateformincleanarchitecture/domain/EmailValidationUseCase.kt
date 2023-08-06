package com.namnp.validateformincleanarchitecture.domain

import android.util.Patterns

class EmailValidationUseCase {
    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's an invalid email"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}