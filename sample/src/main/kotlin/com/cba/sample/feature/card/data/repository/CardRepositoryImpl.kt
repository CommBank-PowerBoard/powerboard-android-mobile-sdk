package com.cba.sample.feature.card.data.repository

import com.cba.sample.feature.card.data.api.CardApi
import com.cba.sample.feature.card.data.api.dto.CaptureCardChargeRequest
import com.cba.sample.feature.card.data.api.dto.TokeniseCardRequest
import com.cba.sample.feature.card.data.api.dto.VaultTokenRequest
import com.cba.sample.feature.card.domain.repository.CardRepository
import com.cba.sample.feature.checkout.data.mapper.toDomain
import com.paydock.feature.wallet.domain.model.integration.ChargeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val cardApi: CardApi,
) : CardRepository {

    override suspend fun tokeniseCardDetails(
        request: TokeniseCardRequest,
    ): String =
        withContext(dispatcher) {
            cardApi.tokeniseCardDetails(
                request = request
            ).resource.resourceData
        }

    override suspend fun captureCardCharge(request: CaptureCardChargeRequest): ChargeResponse =
        withContext(dispatcher) {
            cardApi.captureCharge(request = request).toDomain()
        }

    override suspend fun createCardVaultToken(request: VaultTokenRequest): String =
        withContext(dispatcher) {
            cardApi.createVaultToken(request = request).resource.resourceData.token
        }
}