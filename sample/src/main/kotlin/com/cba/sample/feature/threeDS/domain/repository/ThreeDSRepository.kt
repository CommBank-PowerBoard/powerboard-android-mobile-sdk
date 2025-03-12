package com.cba.sample.feature.threeDS.domain.repository

import com.cba.sample.feature.threeDS.data.api.dto.Capture3DSChargeRequest
import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.domain.model.ThreeDSToken
import com.paydock.feature.wallet.domain.model.integration.ChargeResponse

interface ThreeDSRepository {

    suspend fun createIntegrated3dsToken(
        request: CreateIntegratedThreeDSTokenRequest
    ): ThreeDSToken

    suspend fun capture3DSCharge(request: Capture3DSChargeRequest): ChargeResponse
}