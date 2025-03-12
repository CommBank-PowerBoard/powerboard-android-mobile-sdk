package com.cba.sample.feature.threeDS.data.api.dto

import com.cba.sample.core.AMOUNT
import com.cba.sample.core.AU_CURRENCY_CODE
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

sealed class Capture3DSChargeRequest {

    data class CaptureIntegrated3DSChargeRequest(
        val amount: BigDecimal = BigDecimal(AMOUNT),
        val currency: String = AU_CURRENCY_CODE,
        val reference: String = "some_reference",
        val description: String = "some_description",
        @SerializedName("_3ds") val threeDSData: ThreeDSChargeData? = null
    ) : Capture3DSChargeRequest() {
        data class ThreeDSChargeData(
            val id: String
        )
    }
}