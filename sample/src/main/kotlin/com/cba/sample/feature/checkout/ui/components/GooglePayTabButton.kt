package com.cba.sample.feature.checkout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.cba.sample.R
import com.cba.sample.designsystems.theme.SampleTheme

@Composable
fun GooglePayTabButton(isSelected: Boolean, onClick: () -> Unit) {
    TabButton(
        isSelected = isSelected,
        selectedBorderColor = Color.Black,
        selectedBackgroundColor = Color.Black,
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = if (isSelected) R.drawable.ic_google_pay_selected else R.drawable.ic_google_pay_default),
            contentDescription = null
        )
    }
}

@Composable
@Preview
private fun GooglePayTabButtonDefault() {
    SampleTheme {
        GooglePayTabButton(
            isSelected = false
        ) {}
    }
}

@Composable
@Preview
private fun GooglePayTabButtonSelected() {
    SampleTheme {
        GooglePayTabButton(
            isSelected = true
        ) {}
    }
}