package com.cba.sample.feature.checkout.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cba.sample.feature.checkout.StandaloneCheckoutViewModel
import com.paydock.designsystems.components.sheet.SdkBottomSheet
import com.paydock.feature.threeDS.integrated.presentation.Integrated3DSWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Checkout3DSBottomSheet(
    bottom3DSSheetState: SheetState,
    onDismissRequest: () -> Unit,
    vaultToken: String?,
    threeDSToken: String?,
    viewModel: StandaloneCheckoutViewModel,
) {
    if (!vaultToken.isNullOrBlank() && !threeDSToken.isNullOrBlank()) {
        SdkBottomSheet(
            containerColor = Color.White,
            bottomSheetState = bottom3DSSheetState,
            onDismissRequest = onDismissRequest
        ) {
            Integrated3DSWidget(token = threeDSToken) { result ->
                viewModel.handleIntegrated3DSResult(
                    result
                )
            }
        }
    }
}