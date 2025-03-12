package com.cba.sample.feature.threeDS.data.repository

import com.cba.sample.feature.checkout.data.mapper.toDomain
import com.cba.sample.feature.threeDS.data.api.ThreeDSApi
import com.cba.sample.feature.threeDS.data.api.dto.Capture3DSChargeRequest
import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.data.mapper.mapToDomain
import com.cba.sample.feature.threeDS.domain.model.ThreeDSToken
import com.cba.sample.feature.threeDS.domain.repository.ThreeDSRepository
import com.paydock.feature.wallet.domain.model.integration.ChargeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThreeDSRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val threeDSApi: ThreeDSApi,
) : ThreeDSRepository {
    override suspend fun createIntegrated3dsToken(
        request: CreateIntegratedThreeDSTokenRequest
    ): ThreeDSToken =
        withContext(dispatcher) {
            threeDSApi.createIntegrated3dsToken(request = request).mapToDomain()
        }

    override suspend fun capture3DSCharge(request: Capture3DSChargeRequest): ChargeResponse =
        withContext(dispatcher) {
            threeDSApi.capture3DSCharge(request = request).toDomain()
        }
}