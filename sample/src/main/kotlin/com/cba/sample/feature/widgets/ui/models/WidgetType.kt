package com.cba.sample.feature.widgets.ui.models

import com.cba.sample.designsystems.components.list.DisplayableListItem

enum class WidgetType : DisplayableListItem {
    ADDRESS_DETAILS, AFTER_PAY, CREDIT_CARD_DETAILS, GOOGLE_PAY, GIFT_CARD_DETAILS, INTEGRATED_3DS, CLICK_TO_PAY, PAY_PAL;

    override fun displayName(): String = when (this) {
        ADDRESS_DETAILS -> "Address"
        AFTER_PAY -> "Afterpay"
        CLICK_TO_PAY -> "Click to Pay"
        CREDIT_CARD_DETAILS -> "Card Details"
        GIFT_CARD_DETAILS -> "Gift Card"
        GOOGLE_PAY -> "Google Pay"
        INTEGRATED_3DS -> "Integrated 3DS"
        PAY_PAL -> "PayPal"
    }

    override fun displayDescription(): String = when (this) {
        ADDRESS_DETAILS -> "Capture customer address form"
        AFTER_PAY -> "Standalone Afterpay button"
        CLICK_TO_PAY -> "ClickToPay flow"
        CREDIT_CARD_DETAILS -> "Tokensise card details"
        GIFT_CARD_DETAILS -> "Tokensise card details"
        GOOGLE_PAY -> "Standalone Google Pay button"
        INTEGRATED_3DS -> "Integrated 3DS flow"
        PAY_PAL -> "Standalone PayPal button"
    }
}