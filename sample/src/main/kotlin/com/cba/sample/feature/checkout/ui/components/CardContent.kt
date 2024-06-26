package com.cba.sample.feature.checkout.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cba.sample.feature.checkout.CheckoutViewModel
import com.paydock.feature.card.presentation.CardDetailsWidget

@Composable
fun CardContent(viewModel: CheckoutViewModel) {
    CardDetailsWidget(
        modifier = Modifier.padding(vertical = 16.dp),
        completion = viewModel::handleCardResult
    )
}