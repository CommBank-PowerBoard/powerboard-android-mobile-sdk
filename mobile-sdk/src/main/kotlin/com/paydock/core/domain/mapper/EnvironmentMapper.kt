package com.paydock.core.domain.mapper

import com.paydock.core.ClientSDKConstants
import com.paydock.core.MobileSDKConstants
import com.paydock.core.domain.model.Environment

/**
 * Maps the current environment to the corresponding Base url (API endpoint).
 *
 * @receiver Environment The environment for which the SDK base url string is required.
 * @return The Base url.
 */
fun Environment.mapToBaseUrl(): String = when (this) {
    // Determine the base URL based on the specified environment.
    Environment.PRODUCTION -> MobileSDKConstants.BaseUrls.PRODUCTION_BASE_URL
    Environment.PRE_PRODUCTION -> MobileSDKConstants.BaseUrls.PRE_PRODUCTION_BASE_URL
    Environment.STAGING -> MobileSDKConstants.BaseUrls.STAGING_BASE_URL
}

/**
 * Extension function to map an Environment to its corresponding SSL pin sha256 string.
 *
 * This function takes an instance of the `Environment` enum and returns the
 * corresponding SSL pin string from `ClientSDKConstants.SSLPin`.
 *
 * @receiver Environment The environment for which the SSL pin string is required.
 * @return String The corresponding SSL pin string for the given environment.
 */
fun Environment.mapToSSLPin(): String = when (this) {
    Environment.PRODUCTION -> ClientSDKConstants.SSLPin.PROD
    Environment.PRE_PRODUCTION -> ClientSDKConstants.SSLPin.PRE_PRODUCTION
    Environment.STAGING -> ClientSDKConstants.SSLPin.STAGING
}

/**
 * Maps the current environment to the corresponding Client SDK library url.
 *
 * @receiver Environment The environment for which the Client-SDK library url string is required.
 * @return The Client SDK library url.
 */
fun Environment.mapToClientSDKLibrary(): String = when (this) {
    Environment.PRODUCTION -> ClientSDKConstants.Library.PROD
    Environment.PRE_PRODUCTION -> ClientSDKConstants.Library.PRE_PRODUCTION
    Environment.STAGING -> ClientSDKConstants.Library.STAGING
}

/**
 * Maps the current environment to the corresponding Client SDK environment string.
 *
 * @receiver Environment The environment for which the Client-SDK environment name string is required.
 * @return The Client SDK environment string.
 */
fun Environment.mapToClientSDKEnv(): String = when (this) {
    Environment.PRODUCTION -> ClientSDKConstants.Env.PROD_CBA
    Environment.PRE_PRODUCTION -> ClientSDKConstants.Env.PRE_PRODUCTION_CBA
    Environment.STAGING -> ClientSDKConstants.Env.STAGING_CBA
}
