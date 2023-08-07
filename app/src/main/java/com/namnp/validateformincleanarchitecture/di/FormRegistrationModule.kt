package com.namnp.validateformincleanarchitecture.di

import com.namnp.validateformincleanarchitecture.domain.EmailValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.FormRegistrationUseCase
import com.namnp.validateformincleanarchitecture.domain.PasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.RepeatedPasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.TermsValidationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FormRegistrationModule {

    @Provides
    @Singleton
    fun provideUseCases(): FormRegistrationUseCase {
        return FormRegistrationUseCase(
            emailValidationUseCase = EmailValidationUseCase(),
            passwordValidationUseCase = PasswordValidationUseCase(),
            repeatedPasswordValidationUseCase = RepeatedPasswordValidationUseCase(),
            termsValidationUseCase = TermsValidationUseCase()
        )
    }
}