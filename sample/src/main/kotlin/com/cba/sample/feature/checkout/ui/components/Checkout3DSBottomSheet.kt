package com.cba.sample.feature.checkout.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.cba.sample.feature.checkout.StandaloneCheckoutViewModel
import com.cba.sample.feature.style.StylingViewModel
import com.paydock.designsystems.components.sheet.SdkBottomSheet
import com.paydock.feature.threeDS.common.domain.integration.ThreeDSConfig
import com.paydock.feature.threeDS.common.presentation.ui.ThreeDSAppearanceDefaults
import com.paydock.feature.threeDS.integrated.presentation.Integrated3DSWidget

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Checkout3DSBottomSheet(
    bottom3DSSheetState: SheetState,
    onDismissRequest: () -> Unit,
    vaultToken: String?,
    threeDSToken: String?,
    showCloseButton: Boolean = true,
    viewModel: StandaloneCheckoutViewModel,
    stylingViewModel: StylingViewModel
) {
    if (!vaultToken.isNullOrBlank() && !threeDSToken.isNullOrBlank()) {
        SdkBottomSheet(
            containerColor = Color.White,
            bottomSheetState = bottom3DSSheetState,
            onDismissRequest = onDismissRequest,
            enableClose = showCloseButton
        ) {
            val threeDSAppearance by stylingViewModel.integrated3DSWidgetAppearance.collectAsState()
            val currentOrDefaultAppearance =
                threeDSAppearance ?: ThreeDSAppearanceDefaults.appearance()
            // Integrated 3DS Flow
            Integrated3DSWidget(
                config = ThreeDSConfig(token = threeDSToken),
                appearance = currentOrDefaultAppearance
            ) { result ->
                viewModel.handleIntegrated3DSResult(
                    result
                )
            }
        }
    }
}