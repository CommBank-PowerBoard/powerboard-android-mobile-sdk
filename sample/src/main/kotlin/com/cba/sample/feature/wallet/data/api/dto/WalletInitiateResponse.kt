package com.cba.sample.feature.wallet.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class WalletInitiateResponse(
    val error: Any,
    val resource: Resource<WalletDTO>,
    val status: Int,
) {
    data class WalletDTO(
        val token: String,
        val charge: WalletChargeDTO,
    )
}