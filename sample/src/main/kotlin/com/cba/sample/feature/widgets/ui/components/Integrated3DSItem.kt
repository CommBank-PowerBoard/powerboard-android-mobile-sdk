package com.cba.sample.feature.widgets.ui.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.cba.sample.core.THREE_DS_CARD_ERROR
import com.cba.sample.core.TOKENISE_CARD_ERROR
import com.cba.sample.feature.card.CardViewModel
import com.cba.sample.feature.style.StylingViewModel
import com.cba.sample.feature.threeDS.presentation.ThreeDSViewModel
import com.paydock.core.domain.error.displayableMessage
import com.paydock.core.domain.error.toError
import com.paydock.feature.threeDS.common.domain.integration.ThreeDSConfig
import com.paydock.feature.threeDS.common.presentation.ui.ThreeDSAppearanceDefaults
import com.paydock.feature.threeDS.integrated.presentation.Integrated3DSWidget

@Composable
fun Integrated3DSItem(
    context: Context,
    cardViewModel: CardViewModel = hiltViewModel(),
    threeDSViewModel: ThreeDSViewModel = hiltViewModel(),
    stylingViewModel: StylingViewModel,
) {
    val cardUIState by cardViewModel.stateFlow.collectAsState()
    val threeDSUIState by threeDSViewModel.stateFlow.collectAsState()
    // Use LaunchedEffect to execute the API request and state collection only once
    LaunchedEffect(cardViewModel) {
        cardViewModel.resetResultState()
        cardViewModel.tokeniseCardDetails()
    }
    LaunchedEffect(threeDSViewModel) {
        threeDSViewModel.resetResultState()
    }
    val threeDSAppearance by stylingViewModel.integrated3DSWidgetAppearance.collectAsState()
    val currentOrDefaultAppearance = threeDSAppearance ?: ThreeDSAppearanceDefaults.appearance()
    val cardToken = cardUIState.token
    val threeDSToken = threeDSUIState.token
    when {
        !threeDSToken.isNullOrBlank() -> {
            Integrated3DSWidget(
                config = ThreeDSConfig(token = threeDSToken),
                appearance = currentOrDefaultAppearance
            ) { result ->
                result.onSuccess {
                    Log.d("[Integrated3DSWidget]", "Success: $it")
                    Toast.makeText(context, "3DS Result returned [$it]", Toast.LENGTH_SHORT).show()
                }.onFailure {
                    val error = it.toError()
                    Log.d("[Integrated3DSWidget]", "Failure: ${error.displayableMessage}")
                    Toast.makeText(
                        context,
                        "3DS Result failed! [${error.displayableMessage}]",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        !cardToken.isNullOrBlank() -> {
            cardViewModel.resetResultState()
            threeDSViewModel.createIntegrated3dsToken(cardToken)
        }

        !cardUIState.error.isNullOrBlank() -> {
            Toast.makeText(context, cardUIState.error ?: TOKENISE_CARD_ERROR, Toast.LENGTH_SHORT)
                .show()
        }

        !threeDSUIState.error.isNullOrBlank() -> {
            Toast.makeText(context, threeDSUIState.error ?: THREE_DS_CARD_ERROR, Toast.LENGTH_SHORT)
                .show()
        }

        cardUIState.isLoading || threeDSUIState.isLoading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}