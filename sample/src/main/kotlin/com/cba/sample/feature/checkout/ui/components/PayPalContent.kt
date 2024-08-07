package com.cba.sample.feature.checkout.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cba.sample.designsystems.theme.SampleTheme
import com.paydock.feature.charge.domain.model.ChargeResponse
import com.paydock.feature.paypal.presentation.PayPalWidget

@Composable
fun PayPalContent(
    tokenHandler: (onTokenReceived: (String) -> Unit) -> Unit,
    resultHandler: (Result<ChargeResponse>) -> Unit
) {
    PayPalWidget(
        modifier = Modifier.fillMaxWidth(),
        token = tokenHandler,
        requestShipping = false,
        completion = resultHandler
    )
}

@Composable
@Preview
private fun PayPalContentDefault() {
    SampleTheme {
        PayPalContent({}, {})
    }
}