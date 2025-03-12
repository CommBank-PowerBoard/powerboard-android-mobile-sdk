package com.cba.sample.feature.wallet.domain.repository

import com.cba.sample.feature.wallet.data.api.dto.InitiateWalletRequest
import com.cba.sample.feature.wallet.data.model.WalletCharge

interface WalletRepository {

    suspend fun initiateWalletTransaction(
        manualCapture: Boolean,
        request: InitiateWalletRequest,
    ): WalletCharge

    suspend fun captureWalletCharge(chargeId: String): WalletCharge
}