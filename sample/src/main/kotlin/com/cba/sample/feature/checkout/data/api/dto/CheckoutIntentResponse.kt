package com.cba.sample.feature.checkout.data.api.dto

import com.cba.sample.core.data.api.dto.Resource

data class CheckoutIntentResponse(
    val resource: Resource<CheckoutData>,
    val status: Int
)