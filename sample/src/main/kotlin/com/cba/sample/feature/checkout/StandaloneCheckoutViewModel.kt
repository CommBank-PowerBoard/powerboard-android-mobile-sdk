package com.cba.sample.feature.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cba.sample.BuildConfig
import com.cba.sample.core.AU_CURRENCY_CODE
import com.cba.sample.core.CHARGE_TRANSACTION_ERROR
import com.cba.sample.core.THREE_DS_CARD_ERROR
import com.cba.sample.core.THREE_DS_CHARGE_TRANSACTION_ERROR
import com.cba.sample.core.THREE_DS_STATUS_ERROR
import com.cba.sample.core.TOKENISE_CARD_ERROR
import com.cba.sample.core.TOKENISE_CLICK_TO_PAY_ERROR
import com.cba.sample.core.WALLET_CHARGE_TRANSACTION_ERROR
import com.cba.sample.core.WALLET_INITIALISE_ERROR
import com.cba.sample.feature.card.data.api.dto.CaptureCardChargeRequest
import com.cba.sample.feature.card.data.api.dto.VaultTokenRequest
import com.cba.sample.feature.card.domain.usecase.CaptureCardChargeTokenUseCase
import com.cba.sample.feature.card.domain.usecase.CreateCardSessionVaultTokenUseCase
import com.cba.sample.feature.checkout.data.api.dto.ChargesCustomerDTO
import com.cba.sample.feature.threeDS.data.api.dto.Capture3DSChargeRequest
import com.cba.sample.feature.threeDS.data.api.dto.CreateIntegratedThreeDSTokenRequest
import com.cba.sample.feature.threeDS.domain.model.ThreeDSToken
import com.cba.sample.feature.threeDS.domain.usecase.CaptureThreeDSChargeTokenUseCase
import com.cba.sample.feature.threeDS.domain.usecase.CreateIntegratedThreeDSTokenUseCase
import com.cba.sample.feature.wallet.data.api.dto.InitiateWalletRequest
import com.cba.sample.feature.wallet.data.model.WalletCharge
import com.cba.sample.feature.wallet.domain.usecase.CaptureWalletChargeUseCase
import com.cba.sample.feature.wallet.domain.usecase.InitiateWalletTransactionUseCase
import com.paydock.core.presentation.util.WidgetLoadingDelegate
import com.paydock.feature.card.domain.model.integration.CardResult
import com.paydock.feature.threeDS.integrated.domain.model.integration.Integrated3DSResult
import com.paydock.feature.threeDS.integrated.domain.model.integration.enums.IntegratedEventType
import com.paydock.feature.wallet.domain.model.integration.ChargeResponse
import com.paydock.feature.wallet.domain.model.integration.WalletTokenResult
import com.paydock.feature.wallet.domain.model.integration.WalletType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandaloneCheckoutViewModel @Inject constructor(
    private val initiateWalletTransactionUseCase: InitiateWalletTransactionUseCase,
    private val createCardSessionVaultTokenUseCase: CreateCardSessionVaultTokenUseCase,
    private val createIntegratedThreeDSTokenUseCase: CreateIntegratedThreeDSTokenUseCase,
    private val captureCardChargeTokenUseCase: CaptureCardChargeTokenUseCase,
    private val capture3DSChargeTokenUseCase: CaptureThreeDSChargeTokenUseCase,
    private val captureWalletChargeUseCase: CaptureWalletChargeUseCase,
) : ViewModel(), WidgetLoadingDelegate {

    private val _stateFlow: MutableStateFlow<CheckoutUIState> = MutableStateFlow(CheckoutUIState())
    val stateFlow: StateFlow<CheckoutUIState> = _stateFlow

    private val _toastEvents = Channel<String>()
    val toastEvents = _toastEvents.receiveAsFlow()

    fun resetResultState() {
        _stateFlow.update { state ->
            state.copy(
                cardToken = null,
                vaultToken = null,
                walletChargeResult = null,
                threeDSToken = null,
                chargeResult = null,
                error = null
            )
        }
    }

    fun getWalletTokenResultCallback(walletType: WalletType): (onTokenReceived: (Result<WalletTokenResult>) -> Unit) -> Unit =
        { onTokenReceived ->
            resetResultState()
            when (walletType) {
                WalletType.AFTER_PAY -> {
                    val request = createAfterpayWalletRequest()
                    initiateWalletTransactionResult(
                        request = request,
                        callback = onTokenReceived
                    )
                }

                WalletType.GOOGLE -> {
                    val request = createGoogleWalletRequest()
                    initiateWalletTransactionResult(
                        request = request,
                        callback = onTokenReceived
                    )
                }

                WalletType.PAY_PAL -> {
                    val request = createPayPalWalletRequest()
                    initiateWalletTransactionResult(
                        request = request,
                        callback = onTokenReceived
                    )
                }

            }
        }

    private fun createPayPalWalletRequest(): InitiateWalletRequest {
        return InitiateWalletRequest(
            currency = AU_CURRENCY_CODE,
            customer = ChargesCustomerDTO(
                paymentSource = ChargesCustomerDTO.PaymentSourceDTO(
                    gatewayId = BuildConfig.GATEWAY_ID_PAY_PAL,
                    walletType = WalletType.PAY_PAL.type
                )
            )
        )
    }

    private fun createGoogleWalletRequest(): InitiateWalletRequest {
        return InitiateWalletRequest(
            currency = AU_CURRENCY_CODE,
            customer = ChargesCustomerDTO(
                paymentSource = ChargesCustomerDTO.PaymentSourceDTO(
                    gatewayId = BuildConfig.GATEWAY_ID_GOOGLE_PAY,
                    walletType = WalletType.GOOGLE.type
                )
            )
        )

    }

    private fun createAfterpayWalletRequest(): InitiateWalletRequest {
        return InitiateWalletRequest(
            currency = AU_CURRENCY_CODE,
            customer = ChargesCustomerDTO(
                email = "david.cameron@paydock.com",
                firstName = "David",
                lastName = "Cameron",
                paymentSource = ChargesCustomerDTO.PaymentSourceDTO(
                    gatewayId = BuildConfig.GATEWAY_ID_AFTER_PAY,
                    walletType = WalletType.AFTER_PAY.type,
                    addressLine1 = "asd1",
                    addressLine2 = "asd1",
                    addressLine3 = "asd1",
                    city = "city",
                    state = "state",
                    countryCode = "US",
                    postalCode = "12345",
                )
            ),
            meta = InitiateWalletRequest.MetaDTO(
                successUrl = "https://paydock-integration.netlify.app/success",
                errorUrl = "https://paydock-integration.netlify.app/error"
            )
        )
    }

    private fun createSessionVaultToken(cardToken: String) {
        viewModelScope.launch {
            _stateFlow.update { state ->
                state.copy(isLoading = true)
            }
            val request = VaultTokenRequest.CreateCardSessionVaultTokenRequest(token = cardToken)
            val result = createCardSessionVaultTokenUseCase(request)
            result.onSuccess { vaultToken ->
                _stateFlow.update { state ->
                    state.copy(vaultToken = vaultToken)
                }
                createIntegrated3dsToken(vaultToken)
            }
            result.onFailure {
                _stateFlow.update { state ->
                    state.copy(
                        isLoading = false,
                        error = it.message ?: TOKENISE_CARD_ERROR
                    )
                }
            }
        }
    }

    private fun createIntegrated3dsToken(vaultToken: String) {
        viewModelScope.launch {
            _stateFlow.update { state ->
                state.copy(isLoading = true)
            }
            val result =
                createIntegratedThreeDSTokenUseCase(
                    request = CreateIntegratedThreeDSTokenRequest(
                        customer = ChargesCustomerDTO(
                            paymentSource = ChargesCustomerDTO.PaymentSourceDTO(
                                gatewayId = BuildConfig.GATEWAY_ID_MPGS,
                                vaultToken = vaultToken
                            )
                        )
                    )
                )
            handle3DSTokenResult(result)
        }
    }

    private fun handle3DSTokenResult(result: Result<ThreeDSToken>) {
        result.onSuccess { threeDSResult ->
            when (threeDSResult.status) {
                ThreeDSToken.ThreeDSStatus.NOT_SUPPORTED -> threeDSResult.id?.let {
                    captureIntegrated3DSCharge(it)
                }

                ThreeDSToken.ThreeDSStatus.PRE_AUTH_PENDING -> {
                    _stateFlow.update { state ->
                        state.copy(
                            threeDSToken = threeDSResult,
                            isLoading = false,
                            error = null
                        )
                    }
                }

                else -> _stateFlow.update { state ->
                    state.copy(
                        isLoading = false,
                        error = THREE_DS_STATUS_ERROR
                    )
                }
            }
        }
        result.onFailure {
            _stateFlow.update { state ->
                state.copy(
                    isLoading = false,
                    error = it.message ?: THREE_DS_CARD_ERROR
                )
            }
        }
    }

    private fun initiateWalletTransactionResult(
        manualCapture: Boolean = false,
        request: InitiateWalletRequest,
        callback: (Result<WalletTokenResult>) -> Unit,
    ) {
        viewModelScope.launch {
            _stateFlow.update { state ->
                state.copy(isLoading = true)
            }
            val result =
                initiateWalletTransactionUseCase(manualCapture = manualCapture, request = request)
            result.onSuccess { charge ->
                charge.walletToken?.let { callback(Result.success(WalletTokenResult(token = it))) }
                _stateFlow.update { state ->
                    state.copy(isLoading = false, error = null, walletChargeResult = charge)
                }
            }
            result.onFailure {
                _stateFlow.update { state ->
                    callback(Result.failure(it))
                    state.copy(
                        walletChargeResult = null,
                        isLoading = false,
                        error = it.message ?: WALLET_INITIALISE_ERROR
                    )
                }
            }
        }
    }

    fun handleCardResult(result: Result<CardResult>) {
        result.onSuccess {
            createSessionVaultToken(it.token)
        }.onFailure {
            _stateFlow.update { state ->
                state.copy(error = TOKENISE_CARD_ERROR)
            }
        }
    }

    fun handleClickToPayResult(result: Result<String>) {
        result.onSuccess {
            handleTokenResult(it)
            // If successful, we directly start charge flow
            createSessionVaultToken(cardToken = it)
        }.onFailure {
            _stateFlow.update { state ->
                state.copy(error = TOKENISE_CLICK_TO_PAY_ERROR)
            }
        }
    }

    fun handleIntegrated3DSResult(result: Result<Integrated3DSResult>) {
        result.onSuccess {
            when (it.event) {
                IntegratedEventType.CHARGE_AUTH_SUCCESS -> {
                    it.charge3dsId?.let { chargeId ->
                        captureIntegrated3DSCharge(chargeId)
                    }
                    _stateFlow.update { state -> state.copy(threeDSToken = null) }
                }

                IntegratedEventType.CHARGE_AUTH_REJECT -> {
                    _stateFlow.update { state ->
                        state.copy(
                            isLoading = false,
                            error = THREE_DS_CHARGE_TRANSACTION_ERROR,
                            threeDSToken = null
                        )
                    }
                }

                else -> Unit
            }
        }.onFailure {
            _stateFlow.update { state ->
                state.copy(
                    isLoading = false,
                    error = it.message ?: THREE_DS_CHARGE_TRANSACTION_ERROR,
                    threeDSToken = null
                )
            }
        }
    }

    fun handleChargeResult(result: Result<ChargeResponse>) {
        viewModelScope.launch {
            result.onSuccess { charge ->
                _stateFlow.update { state ->
                    state.copy(
                        isLoading = false,
                        chargeResult = charge,
                        threeDSToken = null,
                        vaultToken = null,
                        cardToken = null
                    )
                }
                _toastEvents.send("Transaction Complete📋: \nStatus ⏳: [${charge.resource.data?.status}]")
            }.onFailure {
                _stateFlow.update { state ->
                    state.copy(isLoading = false, error = it.message ?: CHARGE_TRANSACTION_ERROR)
                }
            }
        }
    }

    private fun handleTokenResult(token: String) {
        _stateFlow.update { state ->
            state.copy(cardToken = token)
        }
    }

    private fun captureWalletCharge(chargeId: String) {
        // This is an optional capture charge logic if "?capture=false"
        viewModelScope.launch {
            _stateFlow.update { state ->
                state.copy(isLoading = true)
            }
            val result = captureWalletChargeUseCase(chargeId)
            result.onSuccess { charge ->
                _stateFlow.update { state ->
                    state.copy(
                        isLoading = false,
                        walletChargeResult = state.walletChargeResult?.copy(status = charge.status),
                    )
                }
                _toastEvents.send("Wallet Transaction Complete📋: \nStatus ⏳: [${charge.status}] ")
            }.onFailure {
                _stateFlow.update { state ->
                    state.copy(
                        isLoading = false,
                        error = it.message ?: WALLET_CHARGE_TRANSACTION_ERROR
                    )
                }
            }
        }
    }

    // This with the 3DS flow causes 2 charges to be created!
    private fun captureCardCharge(vaultToken: String) {
        viewModelScope.launch {
            _stateFlow.update { state ->
                state.copy(isLoading = true, threeDSToken = null)
            }
            val request = CaptureCardChargeRequest(
                currency = AU_CURRENCY_CODE, customer = ChargesCustomerDTO(
                    paymentSource = ChargesCustomerDTO.PaymentSourceDTO(
                        gatewayId = BuildConfig.GATEWAY_ID_MPGS,
                        vaultToken = vaultToken
                    )
                )
            )
            handleChargeResult(captureCardChargeTokenUseCase(request))
        }
    }

    private fun captureIntegrated3DSCharge(threeDSChargeId: String) {
        // This capture flow follows the 3DS pre-auth flow
        viewModelScope.launch {
            _stateFlow.update { state ->
                state.copy(isLoading = true, threeDSToken = null)
            }
            val request = Capture3DSChargeRequest.CaptureIntegrated3DSChargeRequest(
                threeDSData = Capture3DSChargeRequest.CaptureIntegrated3DSChargeRequest.ThreeDSChargeData(
                    threeDSChargeId
                )
            )
            handleChargeResult(capture3DSChargeTokenUseCase(request))
        }
    }

    override fun widgetLoadingDidStart() {
        _stateFlow.update { state ->
            state.copy(isLoading = true)
        }
    }

    override fun widgetLoadingDidFinish() {
        _stateFlow.update { state ->
            state.copy(isLoading = false)
        }
    }
}

data class CheckoutUIState(
    val isLoading: Boolean = false,
    val cardToken: String? = null,
    val vaultToken: String? = null,
    val walletChargeResult: WalletCharge? = null,
    val threeDSToken: ThreeDSToken? = null,
    val chargeResult: ChargeResponse? = null,
    val afterPayResult: String? = null,
    val error: String? = null,
)