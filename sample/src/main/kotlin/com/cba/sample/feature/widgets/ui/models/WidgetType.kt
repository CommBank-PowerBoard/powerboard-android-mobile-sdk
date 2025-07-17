package com.cba.sample.feature.widgets.ui.models

import androidx.compose.runtime.Composable
import com.cba.sample.R
import com.cba.sample.designsystems.components.list.DisplayableListItem
import com.paydock.designsystems.components.text.TextAppearance
import com.paydock.designsystems.components.text.TextAppearanceDefaults
import com.paydock.feature.address.presentation.AddressDetailsAppearanceDefaults

enum class WidgetType : DisplayableListItem {
    ADDRESS_DETAILS, AFTER_PAY, CARD_DETAILS, GOOGLE_PAY, GIFT_CARD, INTEGRATED_3DS, CLICK_TO_PAY, PAY_PAL, PAY_PAL_VAULT;

    override fun displayIcon(): Int? = when (this) {
        ADDRESS_DETAILS -> R.drawable.ic_address_widget
        AFTER_PAY -> R.drawable.ic_afterpay_widget
        CLICK_TO_PAY -> R.drawable.ic_click_to_pay_widget
        CARD_DETAILS -> R.drawable.ic_card_widget
        GIFT_CARD -> R.drawable.ic_gift_card_widget
        GOOGLE_PAY -> R.drawable.ic_google_widget
        INTEGRATED_3DS -> R.drawable.ic_integrated_3ds_widget
        PAY_PAL -> R.drawable.ic_paypal_widget
        PAY_PAL_VAULT -> R.drawable.ic_paypal_widget
    }

    override fun displayName(): String = when (this) {
        ADDRESS_DETAILS -> "Address"
        AFTER_PAY -> "Afterpay"
        CLICK_TO_PAY -> "Click to Pay"
        CARD_DETAILS -> "Card Details"
        GIFT_CARD -> "Gift Card"
        GOOGLE_PAY -> "Google Pay"
        INTEGRATED_3DS -> "Integrated 3DS"
        PAY_PAL -> "PayPal"
        PAY_PAL_VAULT -> "PayPal Vault"
    }

    override fun displayDescription(): String = when (this) {
        ADDRESS_DETAILS -> "Capture customer address form"
        AFTER_PAY -> "Standalone Afterpay button"
        CLICK_TO_PAY -> "ClickToPay flow"
        CARD_DETAILS -> "Tokensise card details"
        GIFT_CARD -> "Tokensise card details"
        GOOGLE_PAY -> "Standalone Google Pay button"
        INTEGRATED_3DS -> "Integrated 3DS flow"
        PAY_PAL -> "Standalone PayPal button"
        PAY_PAL_VAULT -> "Link your Paypal account for faster checkout"
    }
}

@Composable
fun WidgetType.toTitleAppearance(): TextAppearance {
    return when (this) {
        WidgetType.ADDRESS_DETAILS -> AddressDetailsAppearanceDefaults.appearance().title
        else -> TextAppearanceDefaults.appearance()
    }
}