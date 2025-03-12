package com.paydock.core.domain.error

import com.paydock.core.domain.error.exceptions.AfterpayException
import com.paydock.core.domain.error.exceptions.CardDetailsException
import com.paydock.core.domain.error.exceptions.ClickToPayException
import com.paydock.core.domain.error.exceptions.GenericException
import com.paydock.core.domain.error.exceptions.GiftCardException
import com.paydock.core.domain.error.exceptions.GooglePayException
import com.paydock.core.domain.error.exceptions.Integrated3DSException
import com.paydock.core.domain.error.exceptions.PayPalDataCollectorException
import com.paydock.core.domain.error.exceptions.PayPalException
import com.paydock.core.domain.error.exceptions.PayPalVaultException
import com.paydock.core.domain.error.exceptions.Standalone3DSException
import com.paydock.core.network.exceptions.UnknownApiException

/**
 * Represents various types of errors that can occur within the application.
 */
sealed interface ErrorModel {

    /**
     * Connection Error: Represents different connection-related issues.
     */
    sealed class ConnectionError : ErrorModel {

        /** Timeout error for connection. */
        data object Timeout : ConnectionError()

        /** Input/Output error for connection. */
        data object IOError : ConnectionError()

        /** Unknown host error for connection. */
        data object UnknownHost : ConnectionError()
    }

    /**
     * Serialization Error: Indicates an error during serialization process.
     *
     * @property throwable The [Throwable] representing the serialization error.
     */
    data class SerializationError(val throwable: Throwable) : ErrorModel

    /**
     * Unknown Error: Represents an unknown error encountered.
     *
     * @property throwable The [Throwable] representing the unknown error.
     */
    data class UnknownError(val throwable: Throwable) : ErrorModel

    /**
     * 3DS Error: Represents errors specific to Standalone 3DS functionality.
     *
     * @property exception The [Standalone3DSException] specific to 3DS.
     */
    data class Standalone3DSError(val exception: Standalone3DSException) : ErrorModel

    /**
     * 3DS Error: Represents errors specific to Integrated 3DS functionality.
     *
     * @property exception The [Integrated3DSException] specific to 3DS.
     */
    data class Integrated3DSError(val exception: Integrated3DSException) : ErrorModel

    /**
     * PayPal Error: Represents errors specific to PayPal functionality.
     *
     * @property exception The [PayPalException] specific to PayPal.
     */
    data class PayPalError(val exception: PayPalException) : ErrorModel

    /**
     * PayPal Vault Error: Represents errors specific to PayPal Vault functionality.
     *
     * @property exception The [PayPalVaultException] specific to PayPal Vault.
     */
    data class PayPalVaultError(val exception: PayPalVaultException) : ErrorModel

    /**
     * PayPal Data Collector Error: Represents errors specific to PayPal Data Collector functionality.
     *
     * @property exception The [PayPalDataCollectorException] specific to PayPal Data Collector.
     */
    data class PayPalDataCollectorError(val exception: PayPalDataCollectorException) : ErrorModel

    /**
     * Click to Pay Error: Represents errors specific to Click to Pay functionality.
     *
     * @property exception The [ClickToPayException] specific to Click to Pay.
     */
    data class ClickToPayError(val exception: ClickToPayException) : ErrorModel

    /**
     * Google Pay Error: Represents errors specific to Google Pay functionality.
     *
     * @property exception The [GooglePayException] specific to Google Pay.
     */
    data class GooglePayError(val exception: GooglePayException) : ErrorModel

    /**
     * Afterpay Error: Represents errors specific to Afterpay functionality.
     *
     * @property exception The [AfterpayException] specific to Afterpay.
     */
    data class AfterpayError(val exception: AfterpayException) : ErrorModel

    /**
     * Gift Card Error: Represents errors specific to Gift Card tokenisation functionality.
     *
     * @property exception The [GiftCardException] specific to Gift Card.
     */
    data class GiftCardError(val exception: GiftCardException) : ErrorModel

    /**
     * Card Details Error: Represents errors specific to Card tokenisation functionality.
     *
     * @property exception The [CardDetailsException] specific to Card details.
     */
    data class CardDetailsError(val exception: CardDetailsException) : ErrorModel
}

/**
 * Converts various types of [Throwable] instances to corresponding [ErrorModel] instances.
 * @return The appropriate [ErrorModel] instance based on the given Throwable.
 */
@Suppress("MaxLineLength", "CyclomaticComplexMethod")
fun Throwable.toError(): ErrorModel {
    return when (this) {
        is UnknownApiException -> ErrorModel.UnknownError(this)
        // Widget Exceptions
        is CardDetailsException -> ErrorModel.CardDetailsError(this)
        is GiftCardException -> ErrorModel.GiftCardError(this)
        is Integrated3DSException -> ErrorModel.Integrated3DSError(this)
        is PayPalException -> ErrorModel.PayPalError(this)
        is PayPalVaultException -> ErrorModel.PayPalVaultError(this)
        is PayPalDataCollectorException -> ErrorModel.PayPalDataCollectorError(this)
        is ClickToPayException -> ErrorModel.ClickToPayError(this)
        is GooglePayException -> ErrorModel.GooglePayError(this)
        is AfterpayException -> ErrorModel.AfterpayError(this)
        // Generic
        is GenericException.TimeoutException -> ErrorModel.ConnectionError.Timeout
        is GenericException.ConnectionException -> ErrorModel.ConnectionError.UnknownHost
        is GenericException.DataParsingException -> ErrorModel.SerializationError(this)
        is GenericException.GeneralException -> ErrorModel.ConnectionError.IOError
        // Fallback
        else -> ErrorModel.UnknownError(this)
    }
}