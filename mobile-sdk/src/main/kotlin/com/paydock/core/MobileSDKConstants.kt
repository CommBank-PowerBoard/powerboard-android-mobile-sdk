package com.paydock.core

import com.paydock.BuildConfig

/**
 * Constants related to the Mobile SDK configuration.
 */
internal object MobileSDKConstants {
    const val MOBILE_SDK_TAG = "[MobileSDK]"
    const val JS_BRIDGE_NAME = "PayDockMobileSDK"
    const val DEFAULT_WEB_URL = "https://paydock.com/"

    /**
     * Constants related to the UI.
     */
    object General {
        // UI Constants
        internal const val SMALL_SCREEN_SIZE = 360
        internal const val MEDIUM_SCREEN_SIZE = 600
        internal const val INPUT_DELAY = 300L
        internal const val DEBOUNCE_DELAY = 500L

        // Animation Constants
        internal const val DEFAULT_ANIMATION_DURATION = 300
        internal const val EXPANSION_TRANSITION_DURATION = 400
    }

    /**
     * A utility object containing reusable regular expressions for input validation.
     */
    object Regex {
        /**
         * A regular expression that matches numeric strings containing only digits (0-9).
         *
         * This regex ensures that the input consists entirely of numeric characters,
         * with no spaces, letters, or special characters.
         *
         * Example:
         * - Valid: "12345", "007"
         * - Invalid: "123a", "12 34", "!123"
         */
        internal val NUMERIC_DIGITS = Regex("^[0-9]+$")
    }

    /**
     * Constants related to the Network.
     */
    object Network {
        /**
         * Object holding SSL pin constants for different environments.
         *
         * The `SSLPin` object contains the SSL pin values for various environments
         * used by the client SDK. These pins are used to ensure the integrity and
         * security of the SSL/TLS connections.
         *
         * The constants defined are:
         * - `PROD`: SSL pin for the production environment.
         * - `PRE_PRODUCTION`: SSL pin for the pre-production environment.
         * - `STAGING`: SSL pin for the staging environment.
         */
        object SSLPin {
            internal const val PROD = "sha256/ANeVqeyziLbTX8TPdQWbFGVb9VXpDDIMvYuDIwORdKU="
            internal const val PRE_PRODUCTION = "sha256/qKd2CMivb7/p4pfHpSEgnTBLlC0DapGIg5deIqBmmSM="
            internal const val STAGING = "sha256/R52wq7mz8B99wah+Vj5xtnS4GHWuCZBOP3aP+GjEOpU="
        }
    }

    /**
     * Constants related to the Base URL.
     */
    object BaseUrls {
        internal const val PRODUCTION_BASE_URL = "api.powerboard.commbank.com.au"
        internal const val PRE_PRODUCTION_BASE_URL = "api.preproduction.powerboard.commbank.com.au"
        internal const val STAGING_BASE_URL = "api.staging.powerboard.commbank.com.au"
    }

    /**
     * Constants related to card details.
     */
    object CardDetailsConfig {
        internal const val CARD_NUMBER_SECTION_SIZE = 4
        internal const val MIN_CREDIT_CARD_LENGTH = 12
        internal const val MAX_CREDIT_CARD_LENGTH = 19
        internal const val MIN_GIFT_CARD_LENGTH = 14
        internal const val MAX_GIFT_CARD_LENGTH = 25
        internal const val MAX_EXPIRY_LENGTH = 4
        internal const val EXPIRY_CHUNK_SIZE = 2
        internal const val CVV_CVC_LENGTH = 3
        internal const val CID3_LENGTH = 3
        internal const val CID_LENGTH = 4
        internal const val EXPIRY_BASE_YEAR = 2000
        internal const val MAX_MONTH_COUNT = 12
        internal const val DEFAULT_ACTION_TEXT = "Submit"
        internal const val DEFAULT_CONSENT_TEXT = "Remember this card for next time."
        internal const val DEFAULT_POLICY_TEXT = "Read our privacy policy"
        internal const val FONT_SCALE_THRESHOLD = 2.0f
    }

    /**
     * Constants related to address configuration.
     */
    object AddressConfig {
        internal const val MAX_SEARCH_RESULTS = 5
    }

    /**
     * Constants related to wallet callback types.
     */
    object WalletCallbackType {
        internal const val TYPE_CREATE_TRANSACTION = "CREATE_TRANSACTION"
        internal const val TYPE_CREATE_SESSION = "CREATE_SESSION"
    }

    /**
     * Constants related to Google Pay configuration.
     */
    object GooglePayConfig {
        @Suppress("TopLevelPropertyNaming")
        internal val ALLOWED_CARD_AUTH_METHODS = listOf("PAN_ONLY", "CRYPTOGRAM_3DS")

        @Suppress("TopLevelPropertyNaming")
        internal val ALLOWED_CARD_NETWORKS = listOf("AMEX", "DISCOVER", "JCB", "MASTERCARD", "VISA")

        internal const val TRANSACTION_PRICE_STATUS = "FINAL"
        internal const val TOKENIZATION_TYPE = "PAYMENT_GATEWAY"

        internal const val ALLOWED_PAYMENT_METHODS_KEY = "allowedPaymentMethods"
        internal const val TOKENIZATION_DATA_KEY = "tokenizationData"
        internal const val TOKEN_KEY = "token"
        internal const val GATEWAY = "paydock"

        /**
         * MIN: Name, country code, and postal code (default).
         * FULL: Name, street address, locality, region, country code, and postal code.
         */
        internal const val BILLING_ADDRESS_FORMAT = "FULL"
        internal const val CARD_PAYMENT_TYPE = "CARD"
    }

    /**
     * Constants related to PayPal configuration.
     */
    object PayPalConfig {
        internal const val PAY_PAL_REDIRECT_PARAM_VALUE =
            "https://paydock.com/paypal/success&native_xo=1"
        internal const val REDIRECT_PARAM_NAME = "redirect_uri"
    }

    /**
     * Constants related to PayPal Vault configuration.
     */
    object PayPalVaultConfig {
        // This needs to match the scheme used in the manifest file
        internal const val URL_SCHEME = "${BuildConfig.LIBRARY_PACKAGE_NAME}.paypal.vault"
        internal const val RETURN_URL = "$URL_SCHEME://vault/success"
        internal const val CANCEL_URL = "$URL_SCHEME://vault/cancel"
    }

    /**
     * Constants related to Afterpay configuration.
     */
    object Afterpay {
        const val USER_INITIATED_ERROR_MESSAGE = "Afterpay: User cancelled the charge"
        const val NO_CHECKOUT_URL_ERROR_MESSAGE = "Afterpay: No checkout URL"
        const val INVALID_CHECKOUT_URL_ERROR_MESSAGE = "Afterpay: Invalid checkout URL"
        const val NO_CHECKOUT_HANDLER_ERROR_MESSAGE = "Afterpay: No checkout handler supplied"
        const val NO_CONFIGURATION_ERROR_MESSAGE = "Afterpay: No configuration supplied"
        const val LANGUAGE_NOT_SUPPORTED_ERROR_MESSAGE = "Afterpay: Language not supported"
    }

    /**
     * Error messages.
     */
    @Suppress("MaxLineLength")
    object Errors {
        const val SOCKET_TIMEOUT_ERROR =
            "The connection timed out. Please check your internet connection and try again."
        const val UNKNOWN_HOST_ERROR =
            "The server could not be found. Please check your internet connection and try again."
        const val IO_ERROR =
            "An error occurred while communicating with the server. Please check your internet connection and try again."
        const val CONNECTION_ERROR =
            "A network error occurred. Please check your internet connection and try again."
        const val SERIALIZATION_ERROR =
            "Troubles with data serialization. Please try again later or contact support for assistance."
        const val CARD_ERROR =
            "An unexpected error occurred while tokenising card details. Please try again later or contact support for assistance."
        const val GOOGLE_PAY_TOKEN_ERROR =
            "There is a problem retrieving the Google Pay token. Please try again later or contact support for assistance."
        const val GOOGLE_PAY_DEV_ERROR =
            "A developer error occurred. Please try again later or contact support for assistance."
        const val GOOGLE_PAY_ERROR =
            "An unexpected error occurred while processing Google Pay. Please try again later or contact support for assistance."
        const val GOOGLE_PAY_INITIALISATION_ERROR = "Unexpected non API exception when trying to retrieve [allowedPaymentMethods] parameter from PaymentRequest!"
        const val GOOGLE_PAY_CANCELLATION_ERROR = "Google Pay charge was cancelled!"
        const val STANDALONE_3DS_ERROR =
            "An unexpected error occurred while processing Standalone 3DS verification. Please try again later or contact support for assistance."
        const val INTEGRATED_3DS_ERROR =
            "An unexpected error occurred while processing Integrated 3DS verification. Please try again later or contact support for assistance."
        const val PAY_PAL_ERROR =
            "An unexpected error occurred while processing PayPal. Please try again later or contact support for assistance."
        const val PAY_PAL_VAULT_ERROR =
            "An unexpected error occurred while processing PayPal Vault. Please try again later or contact support for assistance."
        const val PAY_PAL_DATA_COLLECTOR_ERROR =
            "An unexpected error occurred while processing PayPal Data Collector. Please try again later or contact support for assistance."
        const val PAY_PAL_DATA_COLLECTOR_UNKNOWN_ERROR =
            "An unknown error occurred while trying to initialise the PayPalDataCollector. Please try again later or contact support for assistance."
        const val AFTER_PAY_ERROR =
            "An unexpected error occurred while processing Afterpay. Please try again later or contact support for assistance."
        const val AFTER_PAY_CALLBACK_ERROR =
            "An unexpected error occurred while retrieving checkout token. Please try again later or contact support for assistance."
        const val CLICK_TO_PAY_ERROR =
            "An unexpected error occurred while processing ClickToPay. Please try again later or contact support for assistance."
        const val UNKNOWN_WEB_ERROR =
            "An unexpected error occurred in the web view. Please try again later or contact support for assistance."
        const val DEFAULT_ERROR = "An unknown error occurred. Please try again later."
    }
}