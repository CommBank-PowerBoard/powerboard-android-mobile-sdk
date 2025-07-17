package com.cba.sample.feature.style.ui.components.properties.googlepay

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cba.sample.R
import com.cba.sample.feature.style.ui.components.core.dropdown.DropdownSelector
import com.google.pay.button.ButtonType

@Composable
fun ButtonTypeDropdown(
    modifier: Modifier = Modifier,
    currentButtonType: ButtonType,
    onGooglePayButtonTypeChange: (ButtonType) -> Unit,
) {
    val options = remember {
        listOf(
            "Pay" to ButtonType.Pay,
            "Checkout" to ButtonType.Checkout,
            "Buy" to ButtonType.Buy,
            "Order" to ButtonType.Order,
            "Book" to ButtonType.Book,
            "Donate" to ButtonType.Donate,
            "Plain" to ButtonType.Plain,
            "Subscribe" to ButtonType.Subscribe,
        )
    }

    val selectedOptionString = remember(currentButtonType) {
        options.find { it.second == currentButtonType }?.first
            ?: ButtonType.Pay.name
    }

    DropdownSelector(
        modifier = modifier,
        title = stringResource(R.string.label_googlepay_button_type),
        options = options.map { it.first },
        selectedOption = selectedOptionString,
        onOptionSelected = { newValueString ->
            val newButtonText =
                options.find { it.first == newValueString }?.second
                    ?: ButtonType.Pay
            onGooglePayButtonTypeChange(newButtonText)
        }
    )
}