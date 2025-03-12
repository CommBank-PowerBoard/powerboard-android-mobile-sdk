package com.cba.sample.feature.checkout.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cba.sample.BuildConfig
import com.paydock.core.presentation.util.WidgetLoadingDelegate
import com.paydock.feature.card.domain.model.integration.CardDetailsWidgetConfig
import com.paydock.feature.card.domain.model.integration.CardResult
import com.paydock.feature.card.domain.model.integration.SaveCardConfig
import com.paydock.feature.card.presentation.CardDetailsWidget

@Composable
fun CardContent(
    enabled: Boolean = true,
    loadingDelegate: WidgetLoadingDelegate?,
    resultHandler: (Result<CardResult>) -> Unit
) {
    CardDetailsWidget(
        modifier = Modifier.padding(vertical = 16.dp),
        enabled = enabled,
        config = CardDetailsWidgetConfig(
            accessToken = BuildConfig.WIDGET_ACCESS_TOKEN,
            actionText = "Pay",
            showCardTitle = false,
            collectCardholderName = false,
            allowSaveCard = SaveCardConfig(
                consentText = "Save payment details",
                privacyPolicyConfig = SaveCardConfig.PrivacyPolicyConfig(
                    privacyPolicyURL = "https://www.google.com"
                )
            ),
        ),
        loadingDelegate = loadingDelegate,
        completion = resultHandler
    )
}