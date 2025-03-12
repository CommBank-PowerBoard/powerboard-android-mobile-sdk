package com.cba.sample.feature.account.data.api

import com.cba.sample.BuildConfig
import com.cba.sample.feature.account.data.api.dto.CreateCustomerOTTRequest
import com.cba.sample.feature.account.data.api.dto.CustomerResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AccountApi {

    @POST("/v1/customers")
    suspend fun createCustomer(
        @Header("X-Access-Token") accessToken: String = BuildConfig.API_ACCESS_TOKEN,
        @Body request: CreateCustomerOTTRequest,
    ): CustomerResponse

}