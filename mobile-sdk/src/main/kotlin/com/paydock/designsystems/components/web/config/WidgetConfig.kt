package com.paydock.designsystems.components.web.config

import android.util.Log
import com.paydock.MobileSDK
import com.paydock.core.ClientSDKConstants
import com.paydock.core.MobileSDKConstants
import com.paydock.core.domain.mapper.mapToClientSDKEnv
import com.paydock.core.domain.mapper.mapToClientSDKLibrary
import com.paydock.core.network.extensions.convertToJsonString
import com.paydock.feature.src.domain.model.integration.meta.ClickToPayMeta
import kotlinx.serialization.SerializationException

/**
 * Sealed class representing configurations for different widgets.
 */
internal sealed class WidgetConfig {
    /**
     * Title of the widget.
     */
    abstract val title: String

    /**
     * URL of the JavaScript library required by the widget.
     */
    abstract val jsLibraryUrl: String

    /**
     * Environment for the widget (e.g., 'sandbox', 'production').
     */
    abstract val environment: String

    /**
     * List of events the widget can trigger.
     */
    abstract val events: List<String>

    /**
     * Abstract method to create the widget initialization script.
     *
     * @return Widget initialization script.
     */
    abstract fun createWidget(): String

    /**
     * Configuration for Click to Pay widget.
     *
     * @property title Title for the Click to Pay widget. Default is "Click to Pay".
     * @property jsLibraryUrl URL of the JavaScript library for Click to Pay widget.
     *                        Default is [ClientSDKConstants.CLIENT_SDK_JS_LIBRARY].
     * @property environment Environment for the Click to Pay widget. Default is the environment
     *                        mapped from [MobileSDK.getInstance().environment].
     * @property events List of events supported by the Click to Pay widget.
     *                  Default includes "iframeLoaded", "checkoutReady", "checkoutCompleted", "checkoutError".
     * @property accessToken Access Token for authentication.
     * @property serviceId Service ID for Click to Pay.
     * @property meta Meta data for configuring Click to Pay.
     */
    data class ClickToPayConfig(
        override val title: String = "Click to Pay",
        override val jsLibraryUrl: String = MobileSDK.getInstance().environment.mapToClientSDKLibrary(),
        override val environment: String = MobileSDK.getInstance().environment.mapToClientSDKEnv(),
        override val events: List<String> = listOf(
            "iframeLoaded",
            "checkoutReady",
            "checkoutCompleted",
            "checkoutPopupOpen",
            "checkoutPopupClose",
            "checkoutError"
        ),
        val accessToken: String,
        val serviceId: String,
        val meta: ClickToPayMeta?,
    ) : WidgetConfig() {

        /**
         * Get the JSON string representation of the meta data.
         *
         * @return JSON string representation of the meta data.
         */
        private fun getMetaJson(): String = try {
            meta?.convertToJsonString() ?: "{}"
        } catch (exception: SerializationException) {
            Log.w(MobileSDKConstants.MOBILE_SDK_TAG, exception.message, exception)
            "{}"
        }

        /**
         * Create the widget initialization script for Click to Pay.
         *
         * @return Widget initialization script.
         */
        override fun createWidget(): String {
            return """
                new ${ClientSDKConstants.WIDGET_ID}.ClickToPay(
                    "#${ClientSDKConstants.WIDGET_CONTAINER_ID}",
                    "$serviceId", // service_id
                    "$accessToken", // paydock_public_key_or_access_token
                    ${getMetaJson()}
                );
            """.trimIndent()
        }

    }

    /**
     * `ThreeDSConfigBase` is a sealed class that serves as the base configuration for 3D Secure authentication widgets.
     * It provides a common structure for different types of 3DS configurations, such as Integrated.
     *
     * This class inherits from `WidgetConfig`, indicating that it represents the configuration for a widget.
     *
     * Each concrete subclass (e.g., `Integrated3DSConfig`) defines specific properties
     * and behaviors for its respective 3DS implementation.
     *
     * @see Integrated3DSConfig
     * @see WidgetConfig
     */
    sealed class ThreeDSConfigBase : WidgetConfig() {

        /**
         * Configuration class for the Integrated 3DS (3D Secure) authentication flow.
         *
         * This class encapsulates the necessary parameters to configure and initialize the 3DS authentication widget
         * within a mobile application. It extends `ThreeDSConfigBase` and provides specific configuration for
         * the integrated 3DS experience.
         *
         * @property title The title displayed for the 3DS authentication process. Defaults to "3d secure authentication".
         * @property jsLibraryUrl The URL of the JavaScript library required for the client-side 3DS integration.
         *                       It's dynamically retrieved from the `MobileSDK` environment.
         * @property environment The environment in which the 3DS process is running (e.g., "sandbox", "production").
         *                      It's dynamically retrieved from the `MobileSDK` environment.
         * @property events A list of event names that the client can subscribe to for the 3DS process. These events indicate
         *                  the different stages and outcomes of the authentication flow.
         *                  - "chargeAuthSuccess": Indicates a successful 3DS authentication.
         *                  - "chargeAuthReject": Indicates a failed or rejected 3DS authentication.
         *                  - "additionalDataCollectSuccess": Indicates successful collection of additional data.
         *                  - "additionalDataCollectReject": Indicates a failure in collecting additional data.
         *                  - "chargeAuth": Indicates the start of the 3DS authentication process.
         * @property token The unique token required to initialize the 3DS widget. This token is typically provided
         *                 by the backend server and is essential for the authentication process.
         *
         * @constructor Creates an instance of [Integrated3DSConfig].
         */
        data class Integrated3DSConfig(
            override val title: String = "3D Secure Authentication",
            override val jsLibraryUrl: String = MobileSDK.getInstance().environment.mapToClientSDKLibrary(),
            override val environment: String = MobileSDK.getInstance().environment.mapToClientSDKEnv(),
            override val events: List<String> = listOf(
                "chargeAuthSuccess",
                "chargeAuthReject",
                "additionalDataCollectSuccess",
                "additionalDataCollectReject",
                "chargeAuth"
            ),
            val token: String,
        ) : ThreeDSConfigBase() {
            /**
             * Creates the widget initialization script for integrated 3D Secure.
             *
             * This script initializes the 3D Secure widget using the provided token.
             *
             * @return The widget initialization script.
             */
            override fun createWidget(): String {
                return """
                new ${ClientSDKConstants.WIDGET_ID}.Canvas3ds("#${ClientSDKConstants.WIDGET_CONTAINER_ID}", "$token")
                """.trimIndent()
            }
        }
    }
}
