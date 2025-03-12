package com.cba.sample.feature.account.domain.repository

import com.cba.sample.feature.account.data.api.dto.CreateCustomerOTTRequest
import com.cba.sample.feature.account.domain.model.Customer

interface AccountRepository {

    suspend fun createCustomer(
        request: CreateCustomerOTTRequest,
    ): Customer

}