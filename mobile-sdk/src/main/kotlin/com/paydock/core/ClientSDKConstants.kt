package com.paydock.core

/**
 * Constants related to the Client SDK configuration.
 */
internal object ClientSDKConstants {

    /**
     * HTML Widget ID used to load Client SDK widgets into.
     */
    internal const val WIDGET_CONTAINER_ID = "widgetContainer"
    internal const val WIDGET_ID = "cba"

    /**
     * URL of the JavaScript library for the Client SDK.
     */
    object Library {
        internal const val PROD =
            "https://widget.powerboard.commbank.com.au/sdk/v1.116.4/widget.umd.min.js"
        internal const val PRE_PRODUCTION =
            "https://widget.preproduction.powerboard.commbank.com.au/sdk/v1.116.4/widget.umd.min.js"
        internal const val STAGING =
            "https://widget.staging.powerboard.commbank.com.au/sdk/v1.116.3-beta/widget.umd.min.js"
    }

    /**
     * Constants for different environment configurations.
     */
    object Env {
        internal const val STAGING_CBA = "staging_cba"
        internal const val PRE_PRODUCTION_CBA = "preproduction_cba"
        internal const val PROD_CBA = "production_cba"
    }

}
