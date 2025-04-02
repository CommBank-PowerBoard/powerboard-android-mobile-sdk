package com.paydock.core.domain.error.dto

import com.paydock.core.network.dto.Resource
import com.paydock.core.network.dto.error.ApiError
import com.paydock.core.network.dto.error.ApiErrorResponse
import com.paydock.core.network.dto.error.ErrorDetails
import com.paydock.core.network.dto.error.ErrorMessage
import com.paydock.core.network.dto.error.ErrorSummary
import com.paydock.core.network.dto.error.ErrorSummaryDetails
import kotlinx.serialization.Serializable

@Serializable
data class SdkApiErrorResponse(
    private val response: ApiErrorResponse
) {
    val status: Int
        get() = response.status

    val error: SdkApiError?
        get() = response.error?.let { SdkApiError(it) }

    val resource: SdkResource<Unit>?
        get() = response.resource?.let { SdkResource(it) }

    val summary: SdkErrorSummary
        get() = SdkErrorSummary(response.summary)
}

@Serializable
data class SdkApiError(
    private val error: ApiError
) {
    val message: String
        get() = error.message

    val code: String
        get() = error.code

    val details: SdkErrorDetails?
        get() = error.details?.let { SdkErrorDetails(it) }
}

@Serializable
class SdkErrorDetails(
    private val errorDetails: ErrorDetails
) {
    val gatewaySpecificCode: String?
        get() = errorDetails.gatewaySpecificCode

    val gatewaySpecificDescription: String?
        get() = errorDetails.gatewaySpecificDescription

    val paramName: String?
        get() = errorDetails.paramName

    val description: String?
        get() = errorDetails.description

    val path: String?
        get() = errorDetails.path

    val messages: List<SdkErrorMessage>?
        get() = errorDetails.messages?.map {
            when (it) {
                is ErrorMessage.StringMessage -> SdkErrorMessage.StringMessage(it)
                is ErrorMessage.GatewayError -> SdkErrorMessage.GatewayError(it)
            }
        }

    val statusCode: String?
        get() = errorDetails.statusCode

    val statusCodeDescription: String?
        get() = errorDetails.statusCodeDescription
}

@Serializable
data class SdkResource<T>(
    private val resource: Resource<T>
) {
    val type: String
        get() = resource.type

    val data: T?
        get() = resource.data
}

@Serializable
data class SdkErrorSummary(
    private val summary: ErrorSummary
) {
    val message: String
        get() = summary.message

    val code: String
        get() = summary.code

    val statusCode: String?
        get() = summary.statusCode

    val statusCodeDescription: String?
        get() = summary.statusCodeDescription

    val details: SdkErrorSummaryDetails?
        get() = summary.details?.let { SdkErrorSummaryDetails(it) }
}

@Serializable
data class SdkErrorSummaryDetails(
    private val summaryDetails: ErrorSummaryDetails
) {
    val gatewaySpecificCode: String?
        get() = summaryDetails.gatewaySpecificCode

    val gatewaySpecificDescription: String?
        get() = summaryDetails.gatewaySpecificDescription

    val messages: List<String>?
        get() = summaryDetails.messages

    val description: String?
        get() = summaryDetails.description

    val statusCode: String?
        get() = summaryDetails.statusCode

    val statusCodeDescription: String?
        get() = summaryDetails.statusCodeDescription

}

/**
 * Represents an error message returned by the SDK.
 *
 * This sealed class encapsulates different types of error messages that can be returned by the SDK.
 * It provides a structured way to handle various error scenarios, including simple string messages
 * and more complex gateway-specific errors.
 */
@Serializable
sealed class SdkErrorMessage {
    /**
     * Represents an error message that is a simple string.
     *
     * This class wraps an `ErrorMessage.StringMessage` to provide a more convenient
     * way to access the string message. It implements `SdkErrorMessage` to be used in a broader
     * error handling context within the SDK.
     *
     * @property errorMessage The underlying `ErrorMessage.StringMessage` containing the string.
     * @property message The actual string message extracted from `errorMessage`.
     */
    data class StringMessage(private val errorMessage: ErrorMessage.StringMessage) :
        SdkErrorMessage() {
        val message: String
            get() = errorMessage.message
    }

    /**
     * Represents an error that occurred during communication with a gateway.
     *
     * This class encapsulates detailed information about a gateway-specific error,
     * including error codes, descriptions, and status codes. It extends `SdkErrorMessage`
     * to provide a consistent way of handling errors within the SDK.
     *
     * @property errorMessage The underlying [ErrorMessage.GatewayError] object containing the error details.
     * It's `private` to enforce access via the exposed properties.
     *
     * @property gatewaySpecificCode The specific error code returned by the gateway.
     *             This can be used to identify the particular type of error encountered.
     *             It can be null if no gateway-specific code was provided.
     *
     * @property gatewaySpecificDescription A human-readable description of the gateway-specific error.
     *             This provides context about the error in the gateway's own terminology.
     *             It can be null if no gateway-specific description was provided.
     *
     * @property description A general description of the error.
     *           This is a more generic description that may be shared across different error types.
     *           It can be null if no description was provided.
     *
     * @property statusCode The HTTP status code associated with the error.
     *           This usually indicates the general nature of the error (e.g., 400 Bad Request, 500 Internal Server Error).
     *           It can be null if no status code was provided.
     *
     * @property statusCodeDescription A human-readable description of the HTTP status code.
     *           This provides more context about the meaning of the status code.
     *           It can be null if no status code description was provided.
     */
    data class GatewayError(
        private val errorMessage: ErrorMessage.GatewayError
    ) : SdkErrorMessage() {
        val gatewaySpecificCode: String?
            get() = errorMessage.gatewaySpecificCode
        val gatewaySpecificDescription: String?
            get() = errorMessage.gatewaySpecificDescription
        val description: String?
            get() = errorMessage.description
        val statusCode: String?
            get() = errorMessage.statusCode
        val statusCodeDescription: String?
            get() = errorMessage.statusCodeDescription
    }
}

/**
 * Extension property that provides a error summary message.
 *
 * @return The displayable error message.
 */
val SdkApiErrorResponse?.displayableMessage: String
    get() = this?.summary?.message ?: ""