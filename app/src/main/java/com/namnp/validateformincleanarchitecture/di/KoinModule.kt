package com.namnp.validateformincleanarchitecture.di

import com.namnp.validateformincleanarchitecture.domain.EmailValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.FormRegistrationUseCase
import com.namnp.validateformincleanarchitecture.domain.PasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.RepeatedPasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.TermsValidationUseCase
import com.namnp.validateformincleanarchitecture.presentation.RegisterFormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FormRegistrationUseCase> {
        FormRegistrationUseCase(
            emailValidationUseCase = EmailValidationUseCase(),
            passwordValidationUseCase = PasswordValidationUseCase(),
            repeatedPasswordValidationUseCase = RepeatedPasswordValidationUseCase(),
            termsValidationUseCase = TermsValidationUseCase()
        )
    }

    viewModel { RegisterFormViewModel(get()) }
}