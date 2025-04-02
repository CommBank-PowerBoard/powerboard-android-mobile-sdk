package com.cba.sample.feature.widgets.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.cba.sample.feature.widgets.ui.components.AddressDetailsItem
import com.cba.sample.feature.widgets.ui.components.AfterpayItem
import com.cba.sample.feature.widgets.ui.components.CardDetailsItem
import com.cba.sample.feature.widgets.ui.components.ClickToPayItem
import com.cba.sample.feature.widgets.ui.components.GiftCardItem
import com.cba.sample.feature.widgets.ui.components.GooglePayItem
import com.cba.sample.feature.widgets.ui.components.IntegratedThreeDSItem
import com.cba.sample.feature.widgets.ui.components.PayPalItem
import com.cba.sample.feature.widgets.ui.components.PayPalVaultItem
import com.cba.sample.feature.widgets.ui.models.WidgetType

@Composable
fun WidgetInfoScreen(
    widgetType: WidgetType
) {
    val context = LocalContext.current

    when (widgetType) {
        WidgetType.ADDRESS_DETAILS -> {
            AddressDetailsItem(context = context)
        }

        WidgetType.AFTER_PAY -> {
            AfterpayItem(context = context)
        }

        WidgetType.CARD_DETAILS -> {
            CardDetailsItem(context = context)
        }

        WidgetType.CLICK_TO_PAY -> {
            ClickToPayItem(context = context)
        }

        WidgetType.GIFT_CARD -> {
            GiftCardItem(context = context)
        }

        WidgetType.GOOGLE_PAY -> {
            GooglePayItem(context = context)
        }

        WidgetType.INTEGRATED_3DS -> {
            IntegratedThreeDSItem(context = context)
        }

        WidgetType.PAY_PAL -> {
            PayPalItem(context = context)
        }

        WidgetType.PAY_PAL_VAULT -> {
            PayPalVaultItem(context = context)
        }
    }
}