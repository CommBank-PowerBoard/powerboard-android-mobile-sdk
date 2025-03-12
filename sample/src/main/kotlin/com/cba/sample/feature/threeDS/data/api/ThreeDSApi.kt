package com.cba.sample.feature.threeDS.data.api

import com.cba.sample.BuildConfig
import com.cba.sample.feature.checkout.data.api.dto.CaptureChargeResponse
import com.cba.sample.feature.threeDS.data.api.dto.Capture3DSChargeRequest
import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.data.api.dto.ThreeDSTokenResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ThreeDSApi {

    @POST("/v1/charges/3ds")
    suspend fun createIntegrated3dsToken(
        @Header("X-Access-Token") accessToken: String = BuildConfig.API_ACCESS_TOKEN,
        @Body request: CreateIntegratedThreeDSTokenRequest,
    ): ThreeDSTokenResponse

    @POST("/v1/charges")
    suspend fun capture3DSCharge(
        @Header("X-Access-Token") accessToken: String = BuildConfig.API_ACCESS_TOKEN,
        @Body request: Capture3DSChargeRequest,
    ): CaptureChargeResponse

}