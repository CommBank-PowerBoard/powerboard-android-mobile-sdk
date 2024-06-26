package com.cba.sample.feature.checkout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cba.sample.R
import com.cba.sample.designsystems.theme.PayPalYellow
import com.cba.sample.designsystems.theme.SampleTheme

@Composable
fun PayPalTabButton(isSelected: Boolean, onClick: () -> Unit) {
    TabButton(
        isSelected = isSelected,
        selectedBorderColor = PayPalYellow,
        selectedBackgroundColor = PayPalYellow,
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_paypal),
            contentDescription = null
        )
    }
}

@Composable
@Preview
private fun PayPalTabButtonDefault() {
    SampleTheme {
        PayPalTabButton(
            isSelected = false
        ) {}
    }
}

@Composable
@Preview
private fun PayPalTabButtonSelected() {
    SampleTheme {
        PayPalTabButton(
            isSelected = true
        ) {}
    }
}