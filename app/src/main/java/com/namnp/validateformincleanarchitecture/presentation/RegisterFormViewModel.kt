package com.namnp.validateformincleanarchitecture.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.namnp.validateformincleanarchitecture.domain.EmailValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.PasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.RepeatedPasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.TermsValidationUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterFormViewModel(
    private val validateEmail: EmailValidationUseCase = EmailValidationUseCase(),
    private val validatePassword: PasswordValidationUseCase = PasswordValidationUseCase(),
    private val validateRepeatedPassword: RepeatedPasswordValidationUseCase = RepeatedPasswordValidationUseCase(),
    private val validateTerms: TermsValidationUseCase = TermsValidationUseCase()
): ViewModel() {

    var state by mutableStateOf(RegisterFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent: Flow<ValidationEvent> = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterFormEvent) {
        when(event) {
            is RegisterFormEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email
                )
            }
            is RegisterFormEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password
                )
            }
            is RegisterFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(
                    repeatedPassword = event.repeatedPassword
                )
            }
            is RegisterFormEvent.AcceptTerms -> {
                state = state.copy(
                    acceptedTerms = event.isAccepted
                )
            }
            is RegisterFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(state.password, state.repeatedPassword)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if(hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError =  termsResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed interface ValidationEvent {
        object Success: ValidationEvent
    }
}