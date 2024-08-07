package com.cba.sample.feature.threeDS.domain.repository

import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.domain.model.ThreeDSToken

interface ThreeDSRepository {
    suspend fun createIntegrated3dsToken(
        accessToken: String,
        request: CreateIntegratedThreeDSTokenRequest
    ): ThreeDSToken
}