package com.cba.sample.feature.wallet.data.api

import com.cba.sample.BuildConfig
import com.cba.sample.feature.wallet.data.api.dto.InitiateWalletRequest
import com.cba.sample.feature.wallet.data.api.dto.WalletCaptureResponse
import com.cba.sample.feature.wallet.data.api.dto.WalletInitiateResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface WalletApi {

    @POST("/v1/charges/wallet?capture=false")
    suspend fun initiateWalletTransactionManualCapture(
        @Header("x-user-secret-key") secretKey: String = BuildConfig.SECRET_KEY,
        @Body request: InitiateWalletRequest
    ): WalletInitiateResponse

    @POST("/v1/charges/wallet")
    suspend fun initiateWalletTransaction(
        @Header("x-user-secret-key") secretKey: String = BuildConfig.SECRET_KEY,
        @Body request: InitiateWalletRequest
    ): WalletInitiateResponse

    @POST("/v1/charges/{id}/capture")
    suspend fun captureWalletCharge(
        @Header("x-user-secret-key") secretKey: String = BuildConfig.SECRET_KEY,
        @Path("id") id: String
    ): WalletCaptureResponse


}