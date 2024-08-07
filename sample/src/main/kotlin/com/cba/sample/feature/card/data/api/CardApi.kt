package com.cba.sample.feature.card.data.api

import com.cba.sample.BuildConfig
import com.cba.sample.feature.card.data.api.dto.CaptureCardChargeRequest
import com.cba.sample.feature.card.data.api.dto.TokeniseCardRequest
import com.cba.sample.feature.card.data.api.dto.TokeniseCardResponse
import com.cba.sample.feature.card.data.api.dto.VaultTokenRequest
import com.cba.sample.feature.card.data.api.dto.VaultTokenResponse
import com.paydock.feature.charge.domain.model.ChargeResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CardApi {

    @POST("/v1/payment_sources/tokens")
    suspend fun tokeniseCardDetails(
        @Header("X-Access-Token") accessToken: String,
        @Body request: TokeniseCardRequest
    ): TokeniseCardResponse

    @POST("/v1/vault/payment_sources")
    suspend fun createVaultToken(
        @Header("x-user-secret-key") secretKey: String = BuildConfig.SECRET_KEY,
        @Body request: VaultTokenRequest
    ): VaultTokenResponse

    @POST("/v1/charges")
    suspend fun captureCharge(
        @Header("x-user-secret-key") secretKey: String = BuildConfig.SECRET_KEY,
        @Body request: CaptureCardChargeRequest
    ): ChargeResponse

}