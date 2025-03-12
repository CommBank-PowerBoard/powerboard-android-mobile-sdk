package com.cba.sample.feature.account.domain.usecase

import com.cba.sample.core.extensions.suspendRunCatching
import com.cba.sample.feature.account.data.api.dto.CreateCustomerOTTRequest
import com.cba.sample.feature.account.domain.repository.AccountRepository
import javax.inject.Inject

class CreateCustomerOTTUseCase @Inject constructor(private val repository: AccountRepository) {

    suspend operator fun invoke(request: CreateCustomerOTTRequest) =
        suspendRunCatching {
            repository.createCustomer(request)
        }
}