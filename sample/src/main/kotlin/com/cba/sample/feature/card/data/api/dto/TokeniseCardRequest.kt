package com.cba.sample.feature.card.data.api.dto

import com.cba.sample.BuildConfig
import com.google.gson.annotations.SerializedName

data class TokeniseCardRequest(
    @SerializedName("card_ccv") val cvv: String = "123",
    @SerializedName("card_name") val cardholderName: String = "Carlie Kuvalis",
    @SerializedName("card_number") val cardNumber: String = "2223000000000007",
    @SerializedName("expire_month") val expiryMonth: String = "08",
    @SerializedName("expire_year") val expiryYear: String = "29",
    @SerializedName("store_ccv") val storeCVV: Boolean = true,
    @SerializedName("gateway_id") val gatewayId: String = BuildConfig.GATEWAY_ID_MPGS,
)