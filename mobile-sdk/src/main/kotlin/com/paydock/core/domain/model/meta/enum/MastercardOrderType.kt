package com.paydock.core.domain.model.meta.enum

import kotlinx.serialization.Serializable

/**
 * Represents the order type in Mastercard SRC.
 */
@Serializable
enum class MastercardOrderType {
    /**
     * Split shipment order type.
     */
    SPLIT_SHIPMENT,

    /**
     * Preferred card order type.
     */
    PREFERRED_CARD
}
