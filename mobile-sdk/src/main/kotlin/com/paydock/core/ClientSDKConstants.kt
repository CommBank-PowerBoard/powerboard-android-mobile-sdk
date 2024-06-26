package com.paydock.core

/**
 * Constants related to the Client SDK configuration.
 */
internal object ClientSDKConstants {

    /**
     * HTML Widget ID used to load Client SDK widgets into.
     */
    internal const val WIDGET_CONTAINER_ID = "widgetContainer"

    /**
     * URL of the JavaScript library for the Client SDK.
     */
    object Library {
        internal const val PROD =
            "https://widget.powerboard.commbank.com.au/sdk/v1.106.11/widget.umd.min.js"
        internal const val PRE_PRODUCTION =
            "https://widget.preproduction.powerboard.commbank.com.au/sdk/v1.106.11-beta/widget.umd.min.js"
        internal const val STAGING =
            "https://widget.staging.powerboard.commbank.com.au/sdk/v1.106.11-beta/widget.umd.min.js"
    }

    /**
     * Constants for different environment configurations.
     */
    object Env {
        internal const val STAGING_CBA = "staging_cba"
        internal const val PRE_PRODUCTION_CBA = "preproduction_cba"
        internal const val PROD_CBA = "production_cba"
    }

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
        internal const val PROD = "sha256/IyTwS67ZnIKzBQmXJoZfiF7bMaLPnqX6mY6vSYCv2Ns="
        internal const val PRE_PRODUCTION = "sha256/qYFbu+dE4nuevdBEEpuPZ5zY6j5tCrqp7hD75XQxitU="
        internal const val STAGING = "sha256/Dc4yfwpXlifANUEh9SEbXkURulKVVAO2ZbkTsl1LBjc="
    }

}
