package com.paydock.core.domain.mapper

import com.afterpay.android.AfterpayEnvironment
import com.google.android.gms.wallet.WalletConstants
import com.paydock.core.BaseUnitTest
import com.paydock.core.ClientSDKConstants
import com.paydock.core.MobileSDKConstants
import com.paydock.core.domain.model.Environment
import kotlin.test.Test
import kotlin.test.assertEquals
import com.paypal.android.corepayments.Environment as PayPalEnvironment

class EnvironmentMapperTest : BaseUnitTest() {

    @Test
    fun mapToBaseUrl_production_returnsProductionBaseUrl() {
        val environment = Environment.PRODUCTION
        val expectedBaseUrl = MobileSDKConstants.BaseUrls.PRODUCTION_BASE_URL
        val actualBaseUrl = environment.mapToBaseUrl()
        assertEquals(expectedBaseUrl, actualBaseUrl)
    }

    @Test
    fun mapToBaseUrl_preProduction_returnsSandboxBaseUrl() {
        val environment = Environment.PRE_PRODUCTION
        val expectedBaseUrl = MobileSDKConstants.BaseUrls.PRE_PRODUCTION_BASE_URL
        val actualBaseUrl = environment.mapToBaseUrl()
        assertEquals(expectedBaseUrl, actualBaseUrl)
    }

    @Test
    fun mapToBaseUrl_staging_returnsStagingBaseUrl() {
        val environment = Environment.STAGING
        val expectedBaseUrl = MobileSDKConstants.BaseUrls.STAGING_BASE_URL
        val actualBaseUrl = environment.mapToBaseUrl()
        assertEquals(expectedBaseUrl, actualBaseUrl)
    }

    @Test
    fun mapToClientSDKLibrary_production_returnsProductionLibrary() {
        val environment = Environment.PRODUCTION
        val expectedLibrary = ClientSDKConstants.Library.PROD
        val actualLibrary = environment.mapToClientSDKLibrary()
        assertEquals(expectedLibrary, actualLibrary)
    }

    @Test
    fun mapToClientSDKLibrary_preProduction_returnsPreProductionLibrary() {
        val environment = Environment.PRE_PRODUCTION
        val expectedLibrary = ClientSDKConstants.Library.PRE_PRODUCTION
        val actualLibrary = environment.mapToClientSDKLibrary()
        assertEquals(expectedLibrary, actualLibrary)
    }

    @Test
    fun mapToClientSDKLibrary_staging_returnsStagingLibrary() {
        val environment = Environment.STAGING
        val expectedLibrary = ClientSDKConstants.Library.STAGING
        val actualLibrary = environment.mapToClientSDKLibrary()
        assertEquals(expectedLibrary, actualLibrary)
    }

    @Test
    fun mapToClientSDKEnv_production_returnsProductionEnv() {
        val environment = Environment.PRODUCTION
        val expectedEnv = ClientSDKConstants.Env.PROD_CBA
        val actualEnv = environment.mapToClientSDKEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToClientSDKEnv_preProduction_returnsPreProductionEnv() {
        val environment = Environment.PRE_PRODUCTION
        val expectedEnv = ClientSDKConstants.Env.PRE_PRODUCTION_CBA
        val actualEnv = environment.mapToClientSDKEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToClientSDKEnv_staging_returnsStagingEnv() {
        val environment = Environment.STAGING
        val expectedEnv = ClientSDKConstants.Env.STAGING_CBA
        val actualEnv = environment.mapToClientSDKEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToPayPalEnv_production_returnsLiveEnv() {
        val environment = Environment.PRODUCTION
        val expectedEnv = PayPalEnvironment.LIVE
        val actualEnv = environment.mapToPayPalEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToPayPalEnv_sandbox_returnsSandboxEnv() {
        val environment = Environment.PRE_PRODUCTION
        val expectedEnv = PayPalEnvironment.SANDBOX
        val actualEnv = environment.mapToPayPalEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToPayPalEnv_staging_returnsSandboxEnv() {
        val environment = Environment.STAGING
        val expectedEnv = PayPalEnvironment.SANDBOX
        val actualEnv = environment.mapToPayPalEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToAfterpayEnv_production_returnsProductionEnv() {
        val environment = Environment.PRODUCTION
        val expectedEnv = AfterpayEnvironment.PRODUCTION
        val actualEnv = environment.mapToAfterpayEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToAfterpayEnv_preProduction_returnsSandboxEnv() {
        val environment = Environment.PRE_PRODUCTION
        val expectedEnv = AfterpayEnvironment.SANDBOX
        val actualEnv = environment.mapToAfterpayEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToAfterpayEnv_staging_returnsSandboxEnv() {
        val environment = Environment.STAGING
        val expectedEnv = AfterpayEnvironment.SANDBOX
        val actualEnv = environment.mapToAfterpayEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToGooglePayEnv_production_returnsProductionEnv() {
        val environment = Environment.PRODUCTION
        val expectedEnv = WalletConstants.ENVIRONMENT_PRODUCTION
        val actualEnv = environment.mapToGooglePayEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToGooglePayEnv_preProduction_returnsEnvironmentTest() {
        val environment = Environment.PRE_PRODUCTION
        val expectedEnv = WalletConstants.ENVIRONMENT_TEST
        val actualEnv = environment.mapToGooglePayEnv()
        assertEquals(expectedEnv, actualEnv)
    }

    @Test
    fun mapToGooglePayEnv_staging_returnsEnvironmentTest() {
        val environment = Environment.STAGING
        val expectedEnv = WalletConstants.ENVIRONMENT_TEST
        val actualEnv = environment.mapToGooglePayEnv()
        assertEquals(expectedEnv, actualEnv)
    }
}