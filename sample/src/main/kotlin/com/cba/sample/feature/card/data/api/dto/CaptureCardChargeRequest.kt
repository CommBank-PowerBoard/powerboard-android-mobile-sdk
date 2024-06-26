package com.cba.sample.feature.card.data.api.dto

import com.cba.sample.core.AMOUNT
import com.cba.sample.feature.wallet.data.api.dto.Customer
import java.math.BigDecimal

data class CaptureCardChargeRequest(
    val amount: BigDecimal = BigDecimal(AMOUNT),
    val currency: String,
    val customer: Customer,
)