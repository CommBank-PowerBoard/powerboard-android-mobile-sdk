package com.cba.sample.feature.wallet.data.api.dto

import com.cba.sample.core.AMOUNT
import com.cba.sample.core.AU_CURRENCY_CODE
import com.cba.sample.core.US_COUNTRY_CODE
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Shipping(
    @SerializedName("address_line1") val addressLine1: String = "ship1",
    @SerializedName("address_line2") val addressLine2: String = "ship22",
    @SerializedName("address_line3") val addressLine3: String = "ship3",
    @SerializedName("address_city") val city: String = "shipcity",
    @SerializedName("address_state") val state: String = "shipstate",
    @SerializedName("address_country") val countryCode: String = US_COUNTRY_CODE,
    @SerializedName("address_postcode") val postalCode: String = "123456",
    val amount: BigDecimal = BigDecimal(AMOUNT),
    val contact: Contact = Contact(),
    val currency: String = AU_CURRENCY_CODE
)