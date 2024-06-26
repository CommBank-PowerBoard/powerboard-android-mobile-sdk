package com.cba.sample.feature.wallet.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class WalletCaptureResponse(
    val error: Any,
    val resource: Resource<WalletChargeData>,
    val status: Int
)