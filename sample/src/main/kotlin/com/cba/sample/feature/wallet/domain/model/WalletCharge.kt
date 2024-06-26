package com.cba.sample.feature.wallet.domain.model

data class WalletCharge(
    val walletToken: String? = null,
    val chargeId: String,
    val status: String
)
