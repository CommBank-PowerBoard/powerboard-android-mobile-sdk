package com.cba.sample.feature.threeDS.data.api

import com.cba.sample.BuildConfig
import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.data.api.dto.ThreeDSTokenResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ThreeDSApi {

    @POST("/v1/charges/3ds")
    suspend fun createIntegrated3dsToken(
        @Header("x-user-public-key") publicKey: String = BuildConfig.PUBLIC_KEY,
        @Body request: CreateIntegratedThreeDSTokenRequest
    ): ThreeDSTokenResponse

}