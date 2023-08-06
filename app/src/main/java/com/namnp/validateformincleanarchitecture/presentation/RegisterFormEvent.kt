package com.namnp.validateformincleanarchitecture.presentation

sealed interface RegisterFormEvent {
    data class EmailChanged(val email: String) : RegisterFormEvent
    data class PasswordChanged(val password: String) : RegisterFormEvent
    data class RepeatedPasswordChanged(
        val repeatedPassword: String
    ) : RegisterFormEvent

    data class AcceptTerms(val isAccepted: Boolean) : RegisterFormEvent

    object Submit: RegisterFormEvent
}