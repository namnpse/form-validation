package com.namnp.validateformincleanarchitecture.domain

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)