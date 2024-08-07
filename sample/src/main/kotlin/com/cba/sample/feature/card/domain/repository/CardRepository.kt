package com.cba.sample.feature.card.domain.repository

import com.cba.sample.feature.card.data.api.dto.CaptureCardChargeRequest
import com.cba.sample.feature.card.data.api.dto.TokeniseCardRequest
import com.cba.sample.feature.card.data.api.dto.VaultTokenRequest
import com.paydock.feature.charge.domain.model.ChargeResponse

interface CardRepository {
    suspend fun tokeniseCardDetails(accessToken: String, request: TokeniseCardRequest): String
    suspend fun createCardVaultToken(request: VaultTokenRequest.CreateCardVaultTokenRequest): String
    suspend fun createCardVaultToken(request: VaultTokenRequest.CreateCardSessionVaultTokenRequest): String
    suspend fun captureCardCharge(request: CaptureCardChargeRequest): ChargeResponse
}