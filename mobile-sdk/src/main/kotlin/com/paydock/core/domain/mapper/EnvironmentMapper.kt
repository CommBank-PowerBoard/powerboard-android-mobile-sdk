package com.paydock.core.domain.mapper

import com.afterpay.android.AfterpayEnvironment
import com.google.android.gms.wallet.WalletConstants
import com.paydock.core.ClientSDKConstants
import com.paydock.core.MobileSDKConstants
import com.paydock.core.domain.model.Environment

/**
 * Maps the current environment to the corresponding Base url (API endpoint).
 *
 * @receiver Environment The environment for which the SDK base url string is required.
 * @return The Base url.
 */
internal fun Environment.mapToBaseUrl(): String = when (this) {
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
fun Environment.mapToSSLPin(): List<String> = when (this) {
    Environment.PRODUCTION -> listOf(MobileSDKConstants.Network.SSLPin.PROD)
    Environment.PRE_PRODUCTION -> listOf(MobileSDKConstants.Network.SSLPin.PRE_PRODUCTION)
    Environment.STAGING -> listOf(MobileSDKConstants.Network.SSLPin.STAGING)
}

/**
 * Maps the current environment to the corresponding Client SDK library url.
 *
 * @receiver Environment The environment for which the Client-SDK library url string is required.
 * @return The Client SDK library url.
 */
internal fun Environment.mapToClientSDKLibrary(): String = when (this) {
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
internal fun Environment.mapToClientSDKEnv(): String = when (this) {
    Environment.PRODUCTION -> ClientSDKConstants.Env.PROD_CBA
    Environment.PRE_PRODUCTION -> ClientSDKConstants.Env.PRE_PRODUCTION_CBA
    Environment.STAGING -> ClientSDKConstants.Env.STAGING_CBA
}

/**
 * Maps the [Environment] of the application to the corresponding PayPal environment.
 *
 * This function converts the internal [Environment] type to the equivalent
 * PayPal SDK environment ([com.paypal.android.corepayments.Environment]).
 *
 * The mapping is as follows:
 * - [Environment.PRODUCTION] maps to [com.paypal.android.corepayments.Environment.LIVE]
 * - [Environment.PRE_PRODUCTION] and [Environment.STAGING] map to [com.paypal.android.corepayments.Environment.SANDBOX]
 *
 * @return The corresponding [com.paypal.android.corepayments.Environment].
 */
internal fun Environment.mapToPayPalEnv(): com.paypal.android.corepayments.Environment =
    when (this) {
        Environment.PRODUCTION -> com.paypal.android.corepayments.Environment.LIVE
        Environment.PRE_PRODUCTION, Environment.STAGING -> com.paypal.android.corepayments.Environment.SANDBOX
    }

/**
 * Maps the application's custom `Environment` enum to the corresponding `AfterpayEnvironment`.
 *
 * This function is used to convert the application's internal representation of environments
 * (e.g., `Environment.SANDBOX`, `Environment.STAGING`, `Environment.PRODUCTION`) to the
 * `AfterpayEnvironment` used by the Afterpay SDK.
 *
 * The mapping ensures that:
 * - Both `Environment.SANDBOX` and `Environment.STAGING` are treated as `AfterpayEnvironment.SANDBOX`.
 * - `Environment.PRODUCTION` is directly mapped to `AfterpayEnvironment.PRODUCTION`.
 *
 * @receiver The application's internal `Environment` enum instance.
 * @return The corresponding `AfterpayEnvironment` used by the Afterpay SDK.
 */
internal fun Environment.mapToAfterpayEnv(): AfterpayEnvironment = when (this) {
    Environment.PRE_PRODUCTION, Environment.STAGING -> AfterpayEnvironment.SANDBOX
    Environment.PRODUCTION -> AfterpayEnvironment.PRODUCTION
}

/**
 * Maps an [Environment] to the corresponding Google Pay environment.
 *
 * @return The Google Pay environment constant:
 * - [WalletConstants.ENVIRONMENT_TEST] for pre-production and staging environments.
 * - [WalletConstants.ENVIRONMENT_PRODUCTION] for production.
 */
internal fun Environment.mapToGooglePayEnv(): Int = when (this) {
    Environment.PRE_PRODUCTION, Environment.STAGING -> WalletConstants.ENVIRONMENT_TEST
    Environment.PRODUCTION -> WalletConstants.ENVIRONMENT_PRODUCTION
}