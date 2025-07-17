package com.cba.sample.feature.checkout.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cba.sample.feature.checkout.CheckoutUIState
import com.cba.sample.feature.checkout.StandaloneCheckoutViewModel
import com.cba.sample.feature.style.StylingViewModel
import com.cba.sample.feature.widgets.ui.models.WidgetType
import com.paydock.designsystems.components.sheet.SdkBottomSheet
import com.paydock.feature.wallet.domain.model.integration.WalletType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutBottomSheet(
    bottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
    uiState: CheckoutUIState,
    viewModel: StandaloneCheckoutViewModel,
    stylingViewModel: StylingViewModel
) {
    val scrollState = rememberScrollState()
    val supportedPaymentMethods =
        listOf(
            WidgetType.CARD_DETAILS,
            WidgetType.CLICK_TO_PAY,
            WidgetType.GOOGLE_PAY,
            WidgetType.PAY_PAL,
            WidgetType.AFTER_PAY
        )
    var selectedTab by remember { mutableStateOf(supportedPaymentMethods.first()) }
    SdkBottomSheet(
        bottomSheetState = bottomSheetState,
        onDismissRequest = onDismissRequest,
        enableClose = !uiState.isLoading
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            // If we want to only show the loader in the sheet, we would need to cater for either maintaining selected state to prevent it resetting
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PaymentMethodContainer(
                    paymentMethods = supportedPaymentMethods,
                    selectedTab = selectedTab,
                    onTabSelected = { tab ->
                        selectedTab = tab
                    })

                // Component based on the selected tab with animation
                Crossfade(
                    targetState = selectedTab,
                    label = "content cross fade"
                ) { targetTab ->
                    when (targetTab) {
                        WidgetType.CARD_DETAILS -> {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                CardContent(
                                    stylingViewModel = stylingViewModel,
                                    enabled = !uiState.isLoading,
                                    loadingDelegate = viewModel,
                                    resultHandler = viewModel::handleCardResult
                                )
                            }
                        }

                        WidgetType.CLICK_TO_PAY -> ClickToPayComponent(
                            resultHandler = viewModel::handleClickToPayResult
                        )

                        WidgetType.GOOGLE_PAY -> GooglePayContent(
                            stylingViewModel = stylingViewModel,
                            enabled = !uiState.isLoading,
                            tokenHandler = viewModel.getWalletTokenResultCallback(
                                WalletType.GOOGLE
                            ),
                            loadingDelegate = viewModel,
                            resultHandler = viewModel::handleChargeResult
                        )

                        WidgetType.PAY_PAL -> PayPalContent(
                            stylingViewModel = stylingViewModel,
                            enabled = !uiState.isLoading,
                            tokenHandler = viewModel.getWalletTokenResultCallback(WalletType.PAY_PAL),
                            loadingDelegate = viewModel,
                            resultHandler = viewModel::handleChargeResult
                        )

                        WidgetType.AFTER_PAY -> AfterpayContent(
                            stylingViewModel = stylingViewModel,
                            enabled = !uiState.isLoading,
                            tokenHandler = viewModel.getWalletTokenResultCallback(WalletType.AFTER_PAY),
                            loadingDelegate = viewModel,
                            resultHandler = viewModel::handleChargeResult
                        )

                        else -> Unit
                    }
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}