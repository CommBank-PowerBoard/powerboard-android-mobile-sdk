package com.cba.sample.feature.card.domain.repository

import com.cba.sample.feature.card.data.api.dto.CaptureCardChargeRequest
import com.cba.sample.feature.card.data.api.dto.TokeniseCardRequest
import com.cba.sample.feature.card.data.api.dto.VaultTokenRequest
import com.paydock.feature.wallet.domain.model.integration.ChargeResponse

interface CardRepository {
    suspend fun tokeniseCardDetails(request: TokeniseCardRequest): String
    suspend fun captureCardCharge(request: CaptureCardChargeRequest): ChargeResponse
    suspend fun createCardVaultToken(request: VaultTokenRequest): String
}