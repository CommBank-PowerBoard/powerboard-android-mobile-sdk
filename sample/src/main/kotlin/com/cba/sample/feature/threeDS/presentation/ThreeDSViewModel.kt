package com.cba.sample.feature.threeDS.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cba.sample.core.THREE_DS_CARD_ERROR
import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.domain.usecase.CreateIntegratedThreeDSTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThreeDSViewModel @Inject constructor(
    private val createIntegratedThreeDSTokenUseCase: CreateIntegratedThreeDSTokenUseCase
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ThreeDSUIState> =
        MutableStateFlow(ThreeDSUIState())
    val stateFlow: StateFlow<ThreeDSUIState> = _stateFlow

    fun createIntegrated3dsToken(cardToken: String) {
        viewModelScope.launch {
            _stateFlow.update { state ->
                state.copy(isLoading = true)
            }
            val result =
                createIntegratedThreeDSTokenUseCase(
                    CreateIntegratedThreeDSTokenRequest(token = cardToken)
                )
            result.onSuccess { threeDSResult ->
                _stateFlow.update { state ->
                    state.copy(token = threeDSResult.token, isLoading = false, error = null)
                }
            }
            result.onFailure {
                _stateFlow.update { state ->
                    state.copy(
                        token = null,
                        isLoading = false,
                        error = it.message ?: THREE_DS_CARD_ERROR
                    )
                }
            }
        }
    }

    fun resetResultState() {
        _stateFlow.update { state ->
            state.copy(token = null, error = null)
        }
    }
}

data class ThreeDSUIState(
    val isLoading: Boolean = true,
    val token: String? = null,
    val error: String? = null,
)