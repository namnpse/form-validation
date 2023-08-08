package com.namnp.validateformincleanarchitecture.di

import com.namnp.validateformincleanarchitecture.domain.EmailValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.FormRegistrationUseCase
import com.namnp.validateformincleanarchitecture.domain.PasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.RepeatedPasswordValidationUseCase
import com.namnp.validateformincleanarchitecture.domain.TermsValidationUseCase
import com.namnp.validateformincleanarchitecture.presentation.RegisterFormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    single { EmailValidationUseCase() }
    single { PasswordValidationUseCase() }
    single { RepeatedPasswordValidationUseCase() }
    single { TermsValidationUseCase() }

// more concise way of declare DI
//    single<FormRegistrationUseCase> {
//        FormRegistrationUseCase(
//            emailValidationUseCase = get(),
//            passwordValidationUseCase = get(),
//            repeatedPasswordValidationUseCase = get(),
//            termsValidationUseCase = get()
//        )
//    }
    singleOf(::FormRegistrationUseCase)

//  viewModel { RegisterFormViewModel(get()) }
    viewModelOf(::RegisterFormViewModel)

//    single<FormRegisterRepository> { FormRegisterRepositoryImpl() }
// -> singleOf(::FormRegisterRepositoryImpl) { bind<FormRegisterRepository>() }
}