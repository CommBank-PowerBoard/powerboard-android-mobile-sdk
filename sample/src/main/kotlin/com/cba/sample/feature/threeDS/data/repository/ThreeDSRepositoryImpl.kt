package com.cba.sample.feature.threeDS.data.repository

import com.cba.sample.feature.threeDS.data.api.ThreeDSApi
import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.data.mapper.mapToDomain
import com.cba.sample.feature.threeDS.domain.model.ThreeDSToken
import com.cba.sample.feature.threeDS.domain.repository.ThreeDSRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThreeDSRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val threeDSApi: ThreeDSApi
) : ThreeDSRepository {

    override suspend fun createIntegrated3dsToken(
        accessToken: String,
        request: CreateIntegratedThreeDSTokenRequest
    ): ThreeDSToken =
        withContext(dispatcher) {
            val response =
                threeDSApi.createIntegrated3dsToken(accessToken = accessToken, request = request)
            response.mapToDomain()
        }
}