package com.cba.sample.feature.card.domain.usecase

import com.cba.sample.core.extensions.suspendRunCatching
import com.cba.sample.feature.card.data.api.dto.VaultTokenRequest
import com.cba.sample.feature.card.domain.repository.CardRepository
import javax.inject.Inject

class CreateCardSessionVaultTokenUseCase @Inject constructor(private val repository: CardRepository) {

    suspend operator fun invoke(request: VaultTokenRequest.CreateCardSessionVaultTokenRequest) =
        suspendRunCatching {
            repository.createCardVaultToken(request)
        }
}