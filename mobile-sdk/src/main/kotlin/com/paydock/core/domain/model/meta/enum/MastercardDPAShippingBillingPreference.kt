package com.paydock.core.domain.model.meta.enum

import kotlinx.serialization.Serializable

/**
 * Represents the preferences for shipping and billing in Mastercard DPA.
 */
@Serializable
enum class MastercardDPAShippingBillingPreference {
    /**
     * Use full shipping and billing information.
     */
    FULL,

    /**
     * Use only postal country for shipping and billing.
     */
    POSTAL_COUNTRY,

    /**
     * No shipping and billing information.
     */
    NONE
}
