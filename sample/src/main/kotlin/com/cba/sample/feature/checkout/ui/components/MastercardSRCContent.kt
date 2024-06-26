package com.cba.sample.feature.checkout.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cba.sample.BuildConfig
import com.cba.sample.feature.checkout.CheckoutViewModel
import com.paydock.core.domain.model.meta.MastercardSRCMeta
import com.paydock.feature.src.presentation.MastercardSRCClickToPayWidget

@Composable
fun MastercardSRCComponent(isLoading: Boolean, viewModel: CheckoutViewModel) {
    val modifier = remember {
        Modifier
            .fillMaxWidth()
            .height(750.dp) }
    if (!isLoading) {
        MastercardSRCClickToPayWidget(
            modifier = modifier,
            serviceId = BuildConfig.GATEWAY_ID_MASTERCARD_SRC,
            meta = MastercardSRCMeta(
                disableSummaryScreen = true
            ),
            completion = viewModel::handleMastercardSRCResult
        )
    } else {
        Spacer(modifier = modifier)
    }
}