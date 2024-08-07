package com.cba.sample.feature.checkout.domain.usecase

import com.cba.sample.core.extensions.suspendRunCatching
import com.cba.sample.feature.checkout.data.api.dto.CreateIntentRequest
import com.cba.sample.feature.checkout.domain.repository.CheckoutRepository
import javax.inject.Inject

class CreateTemplateCheckoutIntentUseCase @Inject constructor(private val repository: CheckoutRepository) {

    suspend operator fun invoke(request: CreateIntentRequest.TemplateIntentRequest) =
        suspendRunCatching {
            repository.createCheckoutIntentToken(request)
        }
}