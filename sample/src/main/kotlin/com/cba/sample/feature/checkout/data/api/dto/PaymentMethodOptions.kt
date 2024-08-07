package com.cba.sample.feature.checkout.data.api.dto

data class PaymentMethodOptions(
    val card: CardPaymentMethod = CardPaymentMethod()
)