package com.namnp.validateformincleanarchitecture

import com.namnp.validateformincleanarchitecture.di.appModule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class KoinUnitTest: KoinTest {

    @Test
    fun checkAllModules() {
        // ensure all definitions configuration are not missing
        appModule.verify()
    }
}