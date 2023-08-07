package com.namnp.validateformincleanarchitecture.domain

data class FormRegistrationUseCase(
    val emailValidationUseCase: EmailValidationUseCase,
    val passwordValidationUseCase: PasswordValidationUseCase,
    val repeatedPasswordValidationUseCase: RepeatedPasswordValidationUseCase,
    val termsValidationUseCase: TermsValidationUseCase,
)