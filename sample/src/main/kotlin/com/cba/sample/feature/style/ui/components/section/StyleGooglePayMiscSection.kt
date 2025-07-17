package com.cba.sample.feature.style.ui.components.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cba.sample.R
import com.cba.sample.feature.style.ui.components.core.counter.NumberCounter
import com.cba.sample.feature.style.ui.components.properties.googlepay.ButtonTypeDropdown
import com.paydock.feature.googlepay.presentation.GooglePayWidgetAppearance

@Composable
fun StyleGooglePayMiscSection(
    currentAppearance: GooglePayWidgetAppearance,
    onAppearanceChange: (GooglePayWidgetAppearance) -> Unit
) {
    // Derived states remain essential for UDF
    val currentButtonType = currentAppearance.type
    val currentCornerRadius = currentAppearance.cornerRadius

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ButtonTypeDropdown(
            modifier = Modifier.fillMaxWidth(),
            currentButtonType = currentButtonType,
            onGooglePayButtonTypeChange = { newButtonType ->
                onAppearanceChange(
                    currentAppearance.copy(type = newButtonType)
                )
            },
        )

        HorizontalDivider()

        NumberCounter(
            title = stringResource(id = R.string.label_corner_radius_dp),
            value = currentCornerRadius.value.toInt(),
            onValueChange = { newIntValue ->
                onAppearanceChange(currentAppearance.copy(cornerRadius = newIntValue.dp))
            }
        )
    }
}
